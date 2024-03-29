/*
 * Copyright (c) 2009 Kathryn Huxtable and Kenneth Orr.
 *
 * This file is part of the SeaGlass Pluggable Look and Feel.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * $Id: SeaGlassStyle.java 1422 2010-03-15 20:08:52Z kathryn@kathrynhuxtable.org $
 */
package com.seaglasslookandfeel;

import static javax.swing.plaf.synth.SynthConstants.DEFAULT;
import static javax.swing.plaf.synth.SynthConstants.DISABLED;
import static javax.swing.plaf.synth.SynthConstants.ENABLED;
import static javax.swing.plaf.synth.SynthConstants.FOCUSED;
import static javax.swing.plaf.synth.SynthConstants.MOUSE_OVER;
import static javax.swing.plaf.synth.SynthConstants.PRESSED;
import static javax.swing.plaf.synth.SynthConstants.SELECTED;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JComponent;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.synth.ColorType;
import javax.swing.plaf.synth.SynthContext;
import javax.swing.plaf.synth.SynthGraphicsUtils;
import javax.swing.plaf.synth.SynthPainter;
import javax.swing.plaf.synth.SynthStyle;

import com.seaglasslookandfeel.component.SeaGlassBorder;
import com.seaglasslookandfeel.painter.Painter;
import com.seaglasslookandfeel.state.State;
import com.seaglasslookandfeel.util.SeaGlassGraphicsUtils;

import sun.awt.AppContext;

import sun.swing.plaf.synth.SynthUI;

/**
 * <p>A SynthStyle implementation used by SeaGlass. Each Region that has been
 * registered with the SeaGlassLookAndFeel will have an associated
 * SeaGlassStyle. Third party components that are registered with the
 * SeaGlassLookAndFeel will therefore be handed a SeaGlassStyle from the look
 * and feel from the #getStyle(JComponent, Region) method.</p>
 *
 * <p>Based on NimbusStyle by Richard Bair and Jasper Potts. Reimplemented
 * because too much is package local.</p>
 *
 * <p>This class properly reads and retrieves values placed in the UIDefaults
 * according to the standard SeaGlass/Nimbus naming conventions. It will create
 * and retrieve painters, fonts, colors, and other data stored there.</p>
 *
 * <p>SeaGlassStyle also supports the ability to override settings on a per
 * component basis. SeaGlassStyle checks the component's client property map for
 * "SeaGlass.Overrides". If the value associated with this key is an instance of
 * UIDefaults, then the values in that defaults table will override the standard
 * SeaGlass defaults in UIManager, but for that component instance only.</p>
 *
 * <p>Optionally, you may specify the client property
 * "SeaGlass.Overrides.InheritDefaults". If true, this client property indicates
 * that the defaults located in UIManager should first be read, and then
 * replaced with defaults located in the component client properties. If false,
 * then only the defaults located in the component client property map will be
 * used. If not specified, it is assumed to be true.</p>
 *
 * <p>You must specify "SeaGlass.Overrides" for
 * "SeaGlass.Overrides.InheritDefaults" to have any effect. "SeaGlass.Overrides"
 * indicates whether there are any overrides, while
 * "SeaGlass.Overrides.InheritDefaults" indicates whether those overrides should
 * first be initialized with the defaults from UIManager.</p>
 *
 * <p>The SeaGlassStyle is reloaded whenever a property change event is fired
 * for a component for "SeaGlass.Overrides" or
 * "SeaGlass.Overrides.InheritDefaults". So for example, setting a new
 * UIDefaults on a component would cause the style to be reloaded.</p>
 *
 * <p>The values are only read out of UIManager once, and then cached. If you
 * need to read the values again (for example, if the UI is being reloaded),
 * then discard this SeaGlassStyle and read a new one from SeaGlassLookAndFeel
 * using SeaGlassLookAndFeel.getStyle.</p>
 *
 * <p>The primary API of interest in this class for 3rd party component authors
 * are the three methods which retrieve painters: #getBackgroundPainter,
 * #getForegroundPainter, and #getBorderPainter.</p>
 *
 * <p>SeaGlassStyle allows you to specify custom states, or modify the order of
 * states. Synth (and thus Nimbus and SeaGlass) has the concept of a "state".
 * For example, a JButton might be in the "MOUSE_OVER" state, or the "ENABLED"
 * state, or the "DISABLED" state. These are all "standard" states which are
 * defined in synth, and which apply to all synth Regions.</p>
 *
 * <p>Sometimes, however, you need to have a custom state. For example, you want
 * JButton to render differently if it's parent is a JToolbar. In SeaGlass, you
 * specify these custom states by including a special key in UIDefaults. The
 * following UIDefaults entries define three states for this button:</p>
 *
 * <pre>
 * <code>
 *     JButton.States = Enabled, Disabled, Toolbar
 *     JButton[Enabled].backgroundPainter = somePainter
 *     JButton[Disabled].background = BLUE
 *     JButton[Toolbar].backgroundPainter = someOtherPaint
 * </code>
 * </pre>
 *
 * <p>As you can see, the <code>JButton.States</code> entry lists the states
 * that the JButton style will support. You then specify the settings for each
 * state. If you do not specify the <code>JButton.States</code> entry, then the
 * standard Synth states will be assumed. If you specify the entry but the list
 * of states is empty or null, then the standard synth states will be assumed.
 * </p>
 */
public class SeaGlassStyle extends SynthStyle {

    // Keys and scales for large/small/mini components, based on Apples sizes.
    /** "Large" size variant. */
    public static final String LARGE_KEY = "large";

    /** "Small" size variant. */
    public static final String SMALL_KEY = "small";

    /** "Mini" size variant. */
    public static final String MINI_KEY = "mini";
    
    /** "Natural" size variant. */
    public static final String NATURAL_KEY = "natural";

    /** Scale factor for "large" size variant. */
    public static final double LARGE_SCALE = 1.15;

    /** Scale factor for "small" size variant. */
    public static final double SMALL_SCALE = 0.857;

    /** Scale factor for "mini" size variant. */
    public static final double MINI_SCALE = 0.714;

    /** Scale factor for "natural" size variant. */
    public static final double NATURAL_SCALE = 1.0;

    /**
     * Special constant used for performance reasons during the get() method. If
     * get() runs through all of the search locations and determines that there
     * is no value, then NULL will be placed into the values map. This way on
     * subsequent lookups it will simply extract NULL, see it, and return null
     * rather than continuing the lookup procedure.
     */
    private static final Object NULL = '\0';

    /**
     * Simple Comparator for ordering the RuntimeStates according to their rank.
     */
    private static final Comparator<RuntimeState> STATE_COMPARATOR = new Comparator<RuntimeState>() {
        public int compare(RuntimeState a, RuntimeState b) {
            return a.state - b.state;
        }
    };

    /** Shared SynthGraphics. */
    private static final SynthGraphicsUtils SEAGLASS_GRAPHICS = new SeaGlassGraphicsUtils();

    /**
     * <p>The Color to return from getColorForState if it would otherwise have
     * returned null.</p>
     *
     * <p>Returning null from getColorForState is a very bad thing, as it causes
     * the AWT peer for the component to install a SystemColor, which is not a
     * UIResource. As a result, if <code>null</code> is returned from
     * getColorForState, then thereafter the color is not updated for other
     * states or on LAF changes or updates. This defaultColor is used to ensure
     * that a ColorUIResource is always returned from getColorForState.</p>
     */
    private Color defaultColor = UIManager.getColor("seaGlassStyleDefaultColor");

    /**
     * The prefix for the component or region that this SeaGlassStyle
     * represents. This prefix is used to lookup state in the UIManager. It
     * should be something like Button or Slider.Thumb or "MyButton" or
     * ComboBox."ComboBox.arrowButton" or "MyComboBox"."ComboBox.arrowButton"
     */
    private String prefix;

    /**
     * The SynthPainter that will be returned from this SeaGlassStyle. The
     * SynthPainter returned will be a SynthPainterImpl, which will in turn
     * delegate back to this SeaGlassStyle for the proper Painter (not
     * SynthPainter) to use for painting the foreground, background, or border.
     */
    private SynthPainter painter;

    /**
     * Data structure containing all of the defaults, insets, states, and other
     * values associated with this style. This instance refers to default
     * values, and are used when no overrides are discovered in the client
     * properties of a component. These values are lazily created on first
     * access.
     */
    private Values values;

    /**
     * A temporary CacheKey used to perform lookups. This pattern avoids
     * creating useless garbage keys, or concatenating strings, etc.
     */
    private CacheKey tmpKey = new CacheKey("", 0);

    /**
     * Some SeaGlassStyles are created for a specific component only. In
     * SeaGlass, this happens whenever the component has as a client property a
     * UIDefaults which overrides (or supplements) those defaults found in
     * UIManager.
     */
    private JComponent component;

    /**
     * Create a new SeaGlassStyle. Only the prefix must be supplied. At the
     * appropriate time, installDefaults will be called. At that point, all of
     * the state information will be pulled from UIManager and stored locally
     * within this style.
     *
     * @param prefix Something like Button or Slider.Thumb or
     *               org.jdesktop.swingx.JXStatusBar or
     *               ComboBox."ComboBox.arrowButton"
     * @param c      an optional reference to a component that this
     *               SeaGlassStyle should be associated with. This is only used
     *               when the component has SeaGlass overrides registered in its
     *               client properties and should be null otherwise.
     */
    SeaGlassStyle(String prefix, JComponent c) {
        this.component = c;
        this.prefix    = prefix;
        this.painter   = new SeaGlassSynthPainterImpl(this);
    }

    /**
     * Returns the <code>SynthGraphicUtils</code> for the specified context.
     *
     * @param  context SynthContext identifying requester
     *
     * @return SynthGraphicsUtils
     */
    public SynthGraphicsUtils getGraphicsUtils(SynthContext context) {
        return SEAGLASS_GRAPHICS;
    }

    /**
     * Install UI defaults.
     *
     * @param context the SynthContext describing the component/regiont, the
     *                style, and the state.
     * @param ui      the UI delegate.
     */
    public void installDefaults(SeaGlassContext context, SynthUI ui) {
        // Special case the Border as this will likely change when the LAF
        // can have more control over this.
        if (!context.isSubregion()) {
            JComponent c      = context.getComponent();
            Border     border = c.getBorder();

            if (border == null || border instanceof UIResource) {
                c.setBorder(new SeaGlassBorder(ui, getInsets(context, null)));
            }
        }

        installDefaults(context);
    }

    /**
     * Installs the necessary state from this Style on the <code>
     * JComponent</code> from <code>context</code>.
     *
     * <p>Overridden to cause this style to populate itself with data from
     * UIDefaults, if necessary.</p>
     *
     * @param ctx context SynthContext identifying component to install
     *            properties to.
     */
    public void installDefaults(SynthContext ctx) {
        validate();

        // delegate to the superclass to install defaults such as background,
        // foreground, font, and opaque onto the swing component.
        super.installDefaults(ctx);
    }

    /**
     * Internal method to parse a style prefix.
     *
     * @param  key the key to parse.
     *
     * @return the prefix portion of the key.
     */
    static String parsePrefix(String key) {
        if (key == null)
            return null;

        boolean inquotes = false;

        for (int i = 0; i < key.length(); i++) {
            char c = key.charAt(i);

            if (c == '"') {
                inquotes = !inquotes;
            } else if ((c == '[' || c == '.') && !inquotes) {
                return key.substring(0, i);
            }
        }

        return null;
    }

    /**
     * Called by SeaGlassLookAndFeel when the look and feel is being
     * uninstalled. Performs general cleanup of any app-context specific data.
     */
    static void uninitialize() {
        // get the appcontext that we've stored data in
        AppContext ctx = AppContext.getAppContext();

        // get the pcl stored in app context
        PropertyChangeListener pcl = (PropertyChangeListener) ctx.get("SeaGlassStyle.defaults.pcl");

        // if the pcl exists, uninstall it from the UIDefaults tables
        if (pcl != null) {
            UIManager.getDefaults().removePropertyChangeListener(pcl);
            UIManager.getLookAndFeelDefaults().removePropertyChangeListener(pcl);
        }

        // clear out the compiled defaults
        ctx.put("SeaGlassStyle.defaults", null);
    }

    /**
     * Pulls data out of UIDefaults, if it has not done so already, and sets up
     * the internal state.
     */
    private void validate() {
        // a non-null values object is the flag we use to determine whether
        // to reparse from UIManager.
        if (values != null)
            return;

        // reconstruct this SeaGlassStyle based on the entries in the UIManager
        // and possibly based on any overrides within the component's
        // client properties (assuming such a component exists and contains
        // any SeaGlass.Overrides)
        values = new Values();

        // the profiler revealed that a great deal of CPU time and useless
        // garbage was being produced by this method and the init method. One
        // culprit was the creation and reparsing of the entire UIDefaults
        // map on each call to this method where "values" was null. It turns
        // out this was happening a lot.
        // To remove this bottleneck, we store the compiled TreeMaps of defaults
        // in the appContext for reuse. It is nulled whenever the UIDefaults
        // changes and recomputed when necessary.
        final AppContext ctx = AppContext.getAppContext();

        // fetch the defaults from the app context. If null, then create and
        // store the compiled defaults
        Map<String, TreeMap<String, Object>> compiledDefaults = (Map<String, TreeMap<String, Object>>) ctx.get("SeaGlassStyle.defaults");

        if (compiledDefaults == null) {

            // the entire UIDefaults tables are parsed and compiled into
            // this map of maps. The key of the compiledDefaults is the
            // prefix for each style, while the value is a map of
            // keys->values for that prefix.
            compiledDefaults = new HashMap<String, TreeMap<String, Object>>();

            // get all the defaults from UIManager.getDefaults() and put them
            // into the compiledDefaults
            compileDefaults(compiledDefaults, UIManager.getDefaults());

            // This second statement pulls defaults from the laf defaults
            UIDefaults lafDefaults = UIManager.getLookAndFeelDefaults();

            compileDefaults(compiledDefaults, lafDefaults);

            // if it has not already been done, add a listener to both
            // UIManager.getDefaults() and UIManager.getLookAndFeelDefaults().
            PropertyChangeListener pcl = (PropertyChangeListener) ctx.get("SeaGlassStyle.defaults.pcl");

            // if pcl is null, then it has not yet been registered with
            // the UIManager defaults for this app context
            if (pcl == null) {

                // create a PCL which will simply clear out the compiled
                // defaults from the app context, causing it to be recomputed
                // on subsequent passes
                pcl = new DefaultsListener();

                // add the PCL to both defaults tables that we pay attention
                // to, so that if the UIDefaults are updated, then the
                // precompiled defaults will be cleared from the app context
                // and recomputed on subsequent passes
                UIManager.getDefaults().addPropertyChangeListener(pcl);
                UIManager.getLookAndFeelDefaults().addPropertyChangeListener(pcl);

                // save the PCL to the app context as a marker indicating
                // that the PCL has been registered so we don't end up adding
                // more than one listener to the UIDefaults tables.
                ctx.put("SeaGlassStyle.defaults.pcl", pcl);
            }

            // store the defaults for reuse
            ctx.put("SeaGlassStyle.defaults", compiledDefaults);
        }

        TreeMap<String, Object> defaults = compiledDefaults.get(prefix);

        // inspect the client properties for the key "SeaGlass.Overrides". If
        // the
        // value is an instance of UIDefaults, then these defaults are used
        // in place of, or in addition to, the defaults in UIManager.
        if (component != null) {
            Object o = component.getClientProperty("SeaGlass.Overrides");

            if (o instanceof UIDefaults) {
                Object                  i       = component.getClientProperty("SeaGlass.Overrides.InheritDefaults");
                boolean                 inherit = i instanceof Boolean ? (Boolean) i : true;
                UIDefaults              d       = (UIDefaults) o;
                TreeMap<String, Object> map     = new TreeMap<String, Object>();

                for (Object obj : d.keySet()) {

                    if (obj instanceof String) {
                        String key = (String) obj;

                        if (key.startsWith(prefix)) {
                            map.put(key, d.get(key));
                        }
                    }
                }

                if (inherit) {
                    defaults.putAll(map);
                } else {
                    defaults = map;
                }
            }
        }

        // Now that I've accumulated all the defaults pertaining to this
        // style, call init which will read these defaults and configure
        // the default "values".
        init(values, defaults);
    }

    /**
     * Iterates over all the keys in the specified UIDefaults and compiles those
     * keys into the comiledDefaults data structure. It relies on parsing the
     * "prefix" out of the key. If the key is not a String or is null then it is
     * ignored. In all other cases a prefix is parsed out (even if that prefix
     * is the empty String or is a "fake" prefix. That is, suppose you had a key
     * Foo~~MySpecial.KeyThing~~. In this case this is not a SeaGlass formatted
     * key, but we don't care, we treat it as if it is. This doesn't pose any
     * harm, it will simply never be used).
     *
     * @param compiledDefaults the compiled defaults data structure.
     * @param d                the UIDefaults to be parsed.
     */
    private void compileDefaults(Map<String, TreeMap<String, Object>> compiledDefaults, UIDefaults d) {
        for (Map.Entry<Object, Object> entry : d.entrySet()) {

            if (entry.getKey() instanceof String) {
                String key = (String) entry.getKey();
                String kp  = parsePrefix(key);

                if (kp == null)
                    continue;

                TreeMap<String, Object> map = compiledDefaults.get(kp);

                if (map == null) {
                    map = new TreeMap<String, Object>();
                    compiledDefaults.put(kp, map);
                }

                map.put(key, entry.getValue());
            }
        }
    }

    /**
     * Initializes the given <code>Values</code> object with the defaults
     * contained in the given TreeMap.
     *
     * @param v          The Values object to be initialized
     * @param myDefaults a map of UIDefaults to use in initializing the Values.
     *                   This map must contain only keys associated with this
     *                   Style.
     */
    private void init(Values v, TreeMap<String, Object> myDefaults) {
        // a list of the different types of states used by this style. This
        // list may contain only "standard" states (those defined by Synth),
        // or it may contain custom states, or it may contain only "standard"
        // states but list them in a non-standard order.
        List<State> states = new ArrayList<State>();

        // a map of state name to code
        Map<String, Integer> stateCodes = new HashMap<String, Integer>();

        // This is a list of runtime "state" context objects. These contain
        // the values associated with each state.
        List<RuntimeState> runtimeStates = new ArrayList<RuntimeState>();

        // determine whether there are any custom states, or custom state
        // order. If so, then read all those custom states and define the
        // "values" stateTypes to be a non-null array.
        // Otherwise, let the "values" stateTypes be null to indicate that
        // there are no custom states or custom state ordering
        String statesString = (String) myDefaults.get(prefix + ".States");

        if (statesString != null) {
            String[] s = statesString.split(",");

            for (int i = 0; i < s.length; i++) {
                s[i] = s[i].trim();

                if (!State.isStandardStateName(s[i])) {

                    // this is a non-standard state name, so look for the
                    // custom state associated with it
                    String stateName   = prefix + "." + s[i];
                    State  customState = (State) myDefaults.get(stateName);

                    if (customState != null) {
                        states.add(customState);
                    }
                } else {
                    states.add(State.getStandardState(s[i]));
                }
            }

            // if there were any states defined, then set the stateTypes array
            // to be non-null. Otherwise, leave it null (meaning, use the
            // standard synth states).
            if (states.size() > 0) {
                v.stateTypes = states.toArray(new State[states.size()]);
            }

            // assign codes for each of the state types
            int code = 1;

            for (State state : states) {
                stateCodes.put(state.getName(), code);
                code <<= 1;
            }
        } else {

            // since there were no custom states defined, setup the list of
            // standard synth states. Note that the "v.stateTypes" is not
            // being set here, indicating that at runtime the state selection
            // routines should use standard synth states instead of custom
            // states. I do need to popuplate this temp list now though, so that
            // the remainder of this method will function as expected.
            states.add(State.Enabled);
            states.add(State.MouseOver);
            states.add(State.Pressed);
            states.add(State.Disabled);
            states.add(State.Focused);
            states.add(State.Selected);
            states.add(State.Default);

            // assign codes for the states
            stateCodes.put("Enabled", ENABLED);
            stateCodes.put("MouseOver", MOUSE_OVER);
            stateCodes.put("Pressed", PRESSED);
            stateCodes.put("Disabled", DISABLED);
            stateCodes.put("Focused", FOCUSED);
            stateCodes.put("Selected", SELECTED);
            stateCodes.put("Default", DEFAULT);
        }

        // Now iterate over all the keys in the defaults table
        for (String key : myDefaults.keySet()) {

            // The key is something like JButton.Enabled.backgroundPainter,
            // or JButton.States, or JButton.background.
            // Remove the "JButton." portion of the key
            String temp = key.substring(prefix.length());

            // if there is a " or : then we skip it because it is a subregion
            // of some kind
            if (temp.indexOf('"') != -1 || temp.indexOf(':') != -1)
                continue;

            // remove the separator
            temp = temp.substring(1);
            // At this point, temp may be any of the following:
            // background
            // [Enabled].background
            // [Enabled+MouseOver].background
            // property.foo

            // parse out the states and the property
            String stateString  = null;
            String property     = null;
            int    bracketIndex = temp.indexOf(']');

            if (bracketIndex < 0) {

                // there is not a state string, so property = temp
                property = temp;
            } else {
                stateString = temp.substring(0, bracketIndex);
                property    = temp.substring(bracketIndex + 2);
            }

            // now that I have the state (if any) and the property, get the
            // value for this property and install it where it belongs
            if (stateString == null) {

                // there was no state, just a property. Check for the custom
                // "contentMargins" property (which is handled specially by
                // Synth/SeaGlass). Also check for the property being "States",
                // in which case it is not a real property and should be
                // ignored.
                // otherwise, assume it is a property and install it on the
                // values object
                if ("contentMargins".equals(property)) {
                    v.contentMargins = (Insets) myDefaults.get(key);
                } else if ("States".equals(property)) {
                    // ignore
                } else {
                    v.defaults.put(property, myDefaults.get(key));
                }
            } else {

                // it is possible that the developer has a malformed UIDefaults
                // entry, such that something was specified in the place of
                // the State portion of the key but it wasn't a state. In this
                // case, skip will be set to true
                boolean skip = false;

                // this variable keeps track of the int value associated with
                // the state. See SynthState for details.
                int componentState = 0;

                // Multiple states may be specified in the string, such as
                // Enabled+MouseOver
                String[] stateParts = stateString.split("\\+");

                // For each state, we need to find the State object associated
                // with it, or skip it if it cannot be found.
                for (String s : stateParts) {

                    if (stateCodes.containsKey(s)) {
                        componentState |= stateCodes.get(s);
                    } else {

                        // Was not a state. Maybe it was a subregion or
                        // something
                        // skip it.
                        skip = true;

                        break;
                    }
                }

                if (skip)
                    continue;

                // find the RuntimeState for this State
                RuntimeState rs = null;

                for (RuntimeState s : runtimeStates) {

                    if (s.state == componentState) {
                        rs = s;

                        break;
                    }
                }

                // couldn't find the runtime state, so create a new one
                if (rs == null) {
                    rs = new RuntimeState(componentState, stateString);
                    runtimeStates.add(rs);
                }

                // check for a couple special properties, such as for the
                // painters. If these are found, then set the specially on
                // the runtime state. Else, it is just a normal property,
                // so put it in the UIDefaults associated with that runtime
                // state
                if ("backgroundPainter".equals(property)) {
                    rs.backgroundPainter = getPainter(myDefaults, key);
                } else if ("foregroundPainter".equals(property)) {
                    rs.foregroundPainter = getPainter(myDefaults, key);
                } else if ("borderPainter".equals(property)) {
                    rs.borderPainter = getPainter(myDefaults, key);
                } else {
                    rs.defaults.put(property, myDefaults.get(key));
                }
            }
        }

        // now that I've collected all the runtime states, I'll sort them based
        // on their integer "state" (see SynthState for how this works).
        Collections.sort(runtimeStates, STATE_COMPARATOR);

        // finally, set the array of runtime states on the values object
        v.states = runtimeStates.toArray(new RuntimeState[runtimeStates.size()]);
    }

    /**
     * Determine the painter given the defaults and a key.
     *
     * @param  defaults the defaults.
     * @param  key      the key.
     *
     * @return the painter, if any can be found, {@code null} otherwise.
     */
    private Painter getPainter(TreeMap<String, Object> defaults, String key) {
        Object p = defaults.get(key);

        if (p instanceof UIDefaults.LazyValue) {
            p = ((UIDefaults.LazyValue) p).createValue(UIManager.getDefaults());
        }

        return (p instanceof Painter ? (Painter) p : null);
    }

    /**
     * Returns the Insets that are used to calculate sizing information.
     *
     * <p>Overridden to cause this style to populate itself with data from
     * UIDefaults, if necessary.</p>
     *
     * @param  ctx context SynthContext identifying requester
     * @param  in  insets Insets to place return value in.
     *
     * @return Sizing Insets.
     */
    @Override
    public Insets getInsets(SynthContext ctx, Insets in) {
        if (in == null) {
            in = new Insets(0, 0, 0, 0);
        }

        Values v = getValues(ctx);

        if (v.contentMargins == null) {
            in.bottom = in.top = in.left = in.right = 0;

            return in;
        } else {
            in.bottom = v.contentMargins.bottom;
            in.top    = v.contentMargins.top;
            in.left   = v.contentMargins.left;
            in.right  = v.contentMargins.right;

            // Account for scale
            // The key "JComponent.sizeVariant" is used to match Apple's LAF
            String scaleKey = (String) ctx.getComponent().getClientProperty("JComponent.sizeVariant");

            if (scaleKey != null) {

                if (LARGE_KEY.equals(scaleKey)) {
                    in.bottom *= LARGE_SCALE;
                    in.top    *= LARGE_SCALE;
                    in.left   *= LARGE_SCALE;
                    in.right  *= LARGE_SCALE;
                } else if (SMALL_KEY.equals(scaleKey)) {
                    in.bottom *= SMALL_SCALE;
                    in.top    *= SMALL_SCALE;
                    in.left   *= SMALL_SCALE;
                    in.right  *= SMALL_SCALE;
                } else if (MINI_KEY.equals(scaleKey)) {
                    in.bottom *= MINI_SCALE;
                    in.top    *= MINI_SCALE;
                    in.left   *= MINI_SCALE;
                    in.right  *= MINI_SCALE;
                }
            }

            return in;
        }
    }

    /**
     * Returns the color for the specified state. This should NOT call any
     * methods on the <code>JComponent</code>.
     *
     * <p>Overridden to cause this style to populate itself with data from
     * UIDefaults, if necessary.</p>
     *
     * <p>In addition, SeaGlassStyle handles ColorTypes slightly differently
     * from Synth.</p>
     *
     * <ul>
     *   <li>ColorType.BACKGROUND will equate to the color stored in UIDefaults
     *     named "background".</li>
     *   <li>ColorType.TEXT_BACKGROUND will equate to the color stored in
     *     UIDefaults named "textBackground".</li>
     *   <li>ColorType.FOREGROUND will equate to the color stored in UIDefaults
     *     named "textForeground".</li>
     *   <li>ColorType.TEXT_FOREGROUND will equate to the color stored in
     *     UIDefaults named "textForeground".</li>
     * </ul>
     *
     * @param  ctx  context SynthContext identifying requester
     * @param  type Type of color being requested.
     *
     * @return Color to render with
     */
    @Override
    public Color getColorForState(SynthContext ctx, ColorType type) {
        String key = null;

        if (type == ColorType.BACKGROUND) {
            key = "background";
        } else if (type == ColorType.FOREGROUND) {

            // map FOREGROUND as TEXT_FOREGROUND
            key = "textForeground";
        } else if (type == ColorType.TEXT_BACKGROUND) {
            key = "textBackground";
        } else if (type == ColorType.TEXT_FOREGROUND) {
            key = "textForeground";
        } else if (type == ColorType.FOCUS) {
            key = "focus";
        } else if (type != null) {
            key = type.toString();
        } else {
            return defaultColor;
        }

        Color c = (Color) get(ctx, key);

        // if all else fails, return a default color (which is a
        // ColorUIResource)
        if (c == null)
            c = defaultColor;

        return c;
    }

    /**
     * Returns the font for the specified state. This should NOT call any method
     * on the <code>JComponent</code>.
     *
     * <p>Overridden to cause this style to populate itself with data from
     * UIDefaults, if necessary. If a value named "font" is not found in
     * UIDefaults, then the "defaultFont" font in UIDefaults will be returned
     * instead.</p>
     *
     * @param  ctx context SynthContext identifying requester
     *
     * @return Font to render with
     */
    @Override
    protected Font getFontForState(SynthContext ctx) {
        Font f = (Font) get(ctx, "font");

        if (f == null)
            f = UIManager.getFont("defaultFont");

        // Account for scale
        // The key "JComponent.sizeVariant" is used to match Apple's LAF
        String scaleKey = (String) ctx.getComponent().getClientProperty("JComponent.sizeVariant");

        if (scaleKey != null) {

            if (LARGE_KEY.equals(scaleKey)) {
                f = f.deriveFont(Math.round(f.getSize2D() * LARGE_SCALE));
            } else if (SMALL_KEY.equals(scaleKey)) {
                f = f.deriveFont(Math.round(f.getSize2D() * SMALL_SCALE));
            } else if (MINI_KEY.equals(scaleKey)) {
                f = f.deriveFont(Math.round(f.getSize2D() * MINI_SCALE));
            }
        }

        return f;

    }

    /**
     * Returns the <code>SynthPainter</code> that will be used for painting.
     * This ends up delegating to the Painters installed in this style. It may
     * return null;
     *
     * @param  ctx context SynthContext identifying requester
     *
     * @return SynthPainter to use
     */
    @Override
    public SynthPainter getPainter(SynthContext ctx) {
        return painter;
    }

    /**
     * Returns true if the region is opaque.
     *
     * <p>Overridden to cause this style to populate itself with data from
     * UIDefaults, if necessary. If opacity is not specified in UI defaults,
     * then it defaults to being non-opaque.</p>
     *
     * @param  ctx context SynthContext identifying requester
     *
     * @return true if region is opaque.
     */
    @Override
    public boolean isOpaque(SynthContext ctx) {
        // Force Table CellRenderers to be opaque
        if ("Table.cellRenderer".equals(ctx.getComponent().getName())) {
            return true;
        }

        Boolean opaque = (Boolean) get(ctx, "opaque");

        return opaque == null ? false : opaque;
    }

    /**
     * Getter for a region specific style property.
     *
     * <p>Overridden to cause this style to populate itself with data from
     * UIDefaults, if necessary.</p>
     *
     * <p>Properties in UIDefaults may be specified in a chained manner. For
     * example:</p>
     *
     * <pre>
     * background
     * Button.opacity
     * Button.Enabled.foreground
     * Button.Enabled+Selected.background
     * </pre>
     *
     * <p>In this example, suppose you were in the Enabled+Selected state and
     * searched for "foreground". In this case, we first check for
     * Button.Enabled+Selected.foreground, but no such color exists. We then
     * fall back to the next valid state, in this case,
     * Button.Enabled.foreground, and have a match. So we return it.</p>
     *
     * <p>Again, if we were in the state Enabled and looked for "background", we
     * wouldn't find it in Button.Enabled, or in Button, but would at the top
     * level in UIManager. So we return that value.</p>
     *
     * <p>One special note: the "key" passed to this method could be of the form
     * "background" or "Button.background" where "Button" equals the prefix
     * passed to the SeaGlassStyle constructor. In either case, it looks for
     * "background".</p>
     *
     * @param  ctx SynthContext identifying requester
     * @param  key Property being requested. Must not be null.
     *
     * @return Value of the named property *
     */
    @Override
    public Object get(SynthContext ctx, Object key) {
        Values v = getValues(ctx);

        // strip off the prefix, if there is one.
        String fullKey    = key.toString();
        String partialKey = fullKey.substring(fullKey.indexOf(".") + 1);

        Object obj    = null;
        int    xstate = getExtendedState(ctx, v);

        // check the cache
        tmpKey.init(partialKey, xstate);
        obj = v.cache.get(tmpKey);
        boolean wasInCache = obj != null;

        if (!wasInCache) {

            // Search exact matching states and then lesser matching states
            RuntimeState s         = null;
            int[]        lastIndex = new int[] { -1 };

            while (obj == null && (s = getNextState(v.states, lastIndex, xstate)) != null) {
                obj = s.defaults.get(partialKey);
            }

            // Search Region Defaults
            if (obj == null && v.defaults != null) {
                obj = v.defaults.get(partialKey);
            }

            // return found object
            // Search UIManager Defaults
            if (obj == null)
                obj = UIManager.get(fullKey);

            // Search Synth Defaults for InputMaps
            if (obj == null && partialKey.equals("focusInputMap")) {
                obj = super.get(ctx, fullKey);
            }

            // if all we got was a null, store this fact for later use
            v.cache.put(new CacheKey(partialKey, xstate), obj == null ? NULL : obj);
        }

        // return found object
        return obj == NULL ? null : obj;
    }

    /**
     * Gets the appropriate background Painter, if there is one, for the state
     * specified in the given SynthContext. This method does appropriate
     * fallback searching, as described in #get.
     *
     * @param  ctx The SynthContext. Must not be null.
     *
     * @return The background painter associated for the given state, or null if
     *         none could be found.
     */
    public Painter getBackgroundPainter(SynthContext ctx) {
        Values  v      = getValues(ctx);
        int     xstate = getExtendedState(ctx, v);
        Painter p      = null;

        // check the cache
        tmpKey.init("backgroundPainter$$instance", xstate);
        p = (Painter) v.cache.get(tmpKey);

        if (p != null)
            return p;

        // not in cache, so lookup and store in cache
        RuntimeState s         = null;
        int[]        lastIndex = new int[] { -1 };

        while ((s = getNextState(v.states, lastIndex, xstate)) != null) {

            if (s.backgroundPainter != null) {
                p = s.backgroundPainter;

                break;
            }
        }

        if (p == null)
            p = (Painter) get(ctx, "backgroundPainter");

        if (p != null) {
            v.cache.put(new CacheKey("backgroundPainter$$instance", xstate), p);
        }

        return p;
    }

    /**
     * Gets the appropriate foreground Painter, if there is one, for the state
     * specified in the given SynthContext. This method does appropriate
     * fallback searching, as described in #get.
     *
     * @param  ctx The SynthContext. Must not be null.
     *
     * @return The foreground painter associated for the given state, or null if
     *         none could be found.
     */
    public Painter getForegroundPainter(SynthContext ctx) {
        Values  v      = getValues(ctx);
        int     xstate = getExtendedState(ctx, v);
        Painter p      = null;

        // check the cache
        tmpKey.init("foregroundPainter$$instance", xstate);
        p = (Painter) v.cache.get(tmpKey);

        if (p != null)
            return p;

        // not in cache, so lookup and store in cache
        RuntimeState s         = null;
        int[]        lastIndex = new int[] { -1 };

        while ((s = getNextState(v.states, lastIndex, xstate)) != null) {

            if (s.foregroundPainter != null) {
                p = s.foregroundPainter;

                break;
            }
        }

        if (p == null)
            p = (Painter) get(ctx, "foregroundPainter");

        if (p != null) {
            v.cache.put(new CacheKey("foregroundPainter$$instance", xstate), p);
        }

        return p;
    }

    /**
     * Gets the appropriate border Painter, if there is one, for the state
     * specified in the given SynthContext. This method does appropriate
     * fallback searching, as described in #get.
     *
     * @param  ctx The SynthContext. Must not be null.
     *
     * @return The border painter associated for the given state, or null if
     *         none could be found.
     */
    public Painter getBorderPainter(SynthContext ctx) {
        Values  v      = getValues(ctx);
        int     xstate = getExtendedState(ctx, v);
        Painter p      = null;

        // check the cache
        tmpKey.init("borderPainter$$instance", xstate);
        p = (Painter) v.cache.get(tmpKey);

        if (p != null)
            return p;

        // not in cache, so lookup and store in cache
        RuntimeState s         = null;
        int[]        lastIndex = new int[] { -1 };

        while ((s = getNextState(v.states, lastIndex, xstate)) != null) {

            if (s.borderPainter != null) {
                p = s.borderPainter;

                break;
            }
        }

        if (p == null)
            p = (Painter) get(ctx, "borderPainter");

        if (p != null) {
            v.cache.put(new CacheKey("borderPainter$$instance", xstate), p);
        }

        return p;
    }

    /**
     * Utility method which returns the proper Values based on the given
     * SynthContext. Ensures that parsing of the values has occurred, or
     * reoccurs as necessary.
     *
     * @param  ctx The SynthContext
     *
     * @return a non-null values reference
     */
    private Values getValues(SynthContext ctx) {
        validate();

        return values;
    }

    /**
     * Simple utility method that searchs the given array of Strings for the
     * given string. This method is only called from getExtendedState if the
     * developer has specified a specific state for the component to be in (ie,
     * has "wedged" the component in that state) by specifying they client
     * property "SeaGlass.State".
     *
     * @param  names a non-null array of strings
     * @param  name  the name to look for in the array
     *
     * @return true or false based on whether the given name is in the array
     */
    private boolean contains(String[] names, String name) {
        assert name != null;

        for (int i = 0; i < names.length; i++) {

            if (name.equals(names[i])) {
                return true;
            }
        }

        return false;
    }

    /**
     * <p>Gets the extended state for a given synth context. SeaGlass supports
     * the ability to define custom states. The algorithm used for choosing what
     * style information to use for a given state requires a single integer bit
     * string where each bit in the integer represents a different state that
     * the component is in. This method uses the componentState as reported in
     * the SynthContext, in addition to custom states, to determine what this
     * extended state is.</p>
     *
     * <p>In addition, this method checks the component in the given context for
     * a client property called "SeaGlass.State". If one exists, then it will
     * decompose the String associated with that property to determine what
     * state to return. In this way, the developer can force a component to be
     * in a specific state, regardless of what the "real" state of the component
     * is.</p>
     *
     * <p>The string associated with "SeaGlass.State" would be of the form:</p>
     *
     * <pre>
     * Enabled + CustomState + MouseOver
     * </pre>
     *
     * @param  ctx
     * @param  v
     *
     * @return
     */
    private int getExtendedState(SynthContext ctx, Values v) {
        JComponent c      = ctx.getComponent();
        int        xstate = 0;
        int        mask   = 1;

        // check for the SeaGlass.State client property
        // Performance NOTE: getClientProperty ends up inside a synchronized
        // block, so there is some potential for performance issues here,
        // however I'm not certain that there is one on a modern VM.
        Object property = c.getClientProperty("SeaGlass.State");

        if (property != null) {
            String   stateNames = property.toString();
            String[] states     = stateNames.split("\\+");

            if (v.stateTypes == null) {

                // standard states only
                for (String stateStr : states) {
                    State.StandardState s = State.getStandardState(stateStr);

                    if (s != null)
                        xstate |= s.getState();
                }
            } else {

                // custom states
                for (State s : v.stateTypes) {

                    if (contains(states, s.getName())) {
                        xstate |= mask;
                    }

                    mask <<= 1;
                }
            }
        } else {

            // if there are no custom states defined, then simply return the
            // state that Synth reported
            if (v.stateTypes == null)
                return ctx.getComponentState();

            // there are custom states on this values, so I'll have to iterate
            // over them all and return a custom extended state
            int state = ctx.getComponentState();

            for (State s : v.stateTypes) {

                if (s.isInState(c, state)) {
                    xstate |= mask;
                }

                mask <<= 1;
            }
        }

        return xstate;
    }

    /**
     * <p>Gets the RuntimeState that most closely matches the state in the given
     * context, but is less specific than the given "lastState". Essentially,
     * this allows you to search for the next best state.</p>
     *
     * <p>For example, if you had the following three states:</p>
     *
     * <pre>
     * Enabled
     * Enabled+Pressed
     * Disabled
     * </pre>
     *
     * <p>And you wanted to find the state that best represented
     * ENABLED+PRESSED+FOCUSED and <code>lastState</code> was null (or an empty
     * array, or an array with a single int with index == -1), then
     * Enabled+Pressed would be returned. If you then call this method again but
     * pass the index of Enabled+Pressed as the "lastState", then Enabled would
     * be returned. If you call this method a third time and pass the index of
     * Enabled in as the <code>lastState</code>, then null would be returned.
     * </p>
     *
     * <p>The actual code path for determining the proper state is the same as
     * in Synth.</p>
     *
     * @param  states
     * @param  lastState a 1 element array, allowing me to do pass-by-reference.
     * @param  xstate
     *
     * @return
     */
    private RuntimeState getNextState(RuntimeState[] states, int[] lastState, int xstate) {
        // Use the StateInfo with the most bits that matches that of state.
        // If there are none, then fallback to
        // the StateInfo with a state of 0, indicating it'll match anything.

        // Consider if we have 3 StateInfos a, b and c with states:
        // SELECTED, SELECTED | ENABLED, 0
        //
        // Input Return Value
        // ----- ------------
        // SELECTED a
        // SELECTED | ENABLED b
        // MOUSE_OVER c
        // SELECTED | ENABLED | FOCUSED b
        // ENABLED c

        if (states != null && states.length > 0) {
            int bestCount = 0;
            int bestIndex = -1;
            int wildIndex = -1;

            // if xstate is 0, then search for the runtime state with component
            // state of 0. That is, find the exact match and return it.
            if (xstate == 0) {

                for (int counter = states.length - 1; counter >= 0; counter--) {

                    if (states[counter].state == 0) {
                        lastState[0] = counter;

                        return states[counter];
                    }
                }

                // an exact match couldn't be found, so there was no match.
                lastState[0] = -1;

                return null;
            }

            // xstate is some value != 0

            // determine from which index to start looking. If lastState[0] is
            // -1
            // then we know to start from the end of the state array. Otherwise,
            // we start at the lastIndex - 1.
            int lastStateIndex = lastState == null || lastState[0] == -1 ? states.length : lastState[0];

            for (int counter = lastStateIndex - 1; counter >= 0; counter--) {
                int oState = states[counter].state;

                if (oState == 0) {

                    if (wildIndex == -1) {
                        wildIndex = counter;
                    }
                } else if ((xstate & oState) == oState) {
                    // This is key, we need to make sure all bits of the
                    // StateInfo match, otherwise a StateInfo with
                    // SELECTED | ENABLED would match ENABLED, which we
                    // don't want.

                    // This comes from BigInteger.bitCnt
                    int bitCount = oState;

                    bitCount -= (0xaaaaaaaa & bitCount) >>> 1;
                    bitCount = (bitCount & 0x33333333) + ((bitCount >>> 2) & 0x33333333);
                    bitCount = bitCount + (bitCount >>> 4) & 0x0f0f0f0f;
                    bitCount += bitCount >>> 8;
                    bitCount += bitCount >>> 16;
                    bitCount = bitCount & 0xff;

                    if (bitCount > bestCount) {
                        bestIndex = counter;
                        bestCount = bitCount;
                    }
                }
            }

            if (bestIndex != -1) {
                lastState[0] = bestIndex;

                return states[bestIndex];
            }

            if (wildIndex != -1) {
                lastState[0] = wildIndex;

                return states[wildIndex];
            }
        }

        lastState[0] = -1;

        return null;
    }

    /**
     * Contains values such as the UIDefaults and painters asssociated with a
     * state. Whereas <code>State</code> represents a distinct state that a
     * component can be in (such as Enabled), this class represents the colors,
     * fonts, painters, etc associated with some state for this style.
     */
    private final class RuntimeState implements Cloneable {
        int        state;
        Painter    backgroundPainter;
        Painter    foregroundPainter;
        Painter    borderPainter;
        String     stateName;
        UIDefaults defaults = new UIDefaults(10, .7f);

        /**
         * Creates a new RuntimeState object.
         *
         * @param state     the state.
         * @param stateName the name for the state.
         */
        private RuntimeState(int state, String stateName) {
            this.state     = state;
            this.stateName = stateName;
        }

        /**
         * @see java.lang.Object#toString()
         */
        @Override
        public String toString() {
            return stateName;
        }

        /**
         * @see java.lang.Object#clone()
         */
        @Override
        public RuntimeState clone() {
            RuntimeState clone = new RuntimeState(state, stateName);

            clone.backgroundPainter = backgroundPainter;
            clone.foregroundPainter = foregroundPainter;
            clone.borderPainter     = borderPainter;
            clone.defaults.putAll(defaults);

            return clone;
        }
    }

    /**
     * Essentially a struct of data for a style. A default instance of this
     * class is used by SeaGlassStyle. Additional instances exist for each
     * component that has overrides.
     */
    private static final class Values {

        /**
         * The list of State types. A State represents a type of state, such as
         * Enabled, Default, WindowFocused, etc. These can be custom states.
         */
        State[] stateTypes = null;

        /**
         * The list of actual runtime state representations. These can represent
         * things such as Enabled + Focused. Thus, they differ from States in
         * that they contain several states together, and have associated
         * properties, data, etc.
         */
        RuntimeState[] states = null;

        /** The content margins for this region. */
        Insets contentMargins;

        /** Defaults on the region/component level. */
        UIDefaults defaults = new UIDefaults(10, .7f);

        /**
         * Simple cache. After a value has been looked up, it is stored in this
         * cache for later retrieval. The key is a concatenation of the property
         * being looked up, two dollar signs, and the extended state. So for
         * example:
         *
         * <p>foo.bar$$2353</p>
         */
        Map<CacheKey, Object> cache = new HashMap<CacheKey, Object>();
    }

    /**
     * This implementation presupposes that key is never null and that the two
     * keys being checked for equality are never null
     */
    private static final class CacheKey {
        private String key;
        private int    xstate;

        /**
         * Creates a new CacheKey object.
         *
         * @param key    the key.
         * @param xstate the extended state.
         */
        CacheKey(Object key, int xstate) {
            init(key, xstate);
        }

        /**
         * Initialize the CacheKey object with its values.
         *
         * @param key    the key.
         * @param xstate the extended state.
         */
        void init(Object key, int xstate) {
            this.key    = key.toString();
            this.xstate = xstate;
        }

        /**
         * @see java.lang.Object#equals(java.lang.Object)
         */
        @Override
        public boolean equals(Object obj) {
            final CacheKey other = (CacheKey) obj;

            if (obj == null)
                return false;

            if (this.xstate != other.xstate)
                return false;

            if (!this.key.equals(other.key))
                return false;

            return true;
        }

        /**
         * @see java.lang.Object#hashCode()
         */
        @Override
        public int hashCode() {
            int hash = 3;

            hash = 29 * hash + this.key.hashCode();
            hash = 29 * hash + this.xstate;

            return hash;
        }
    }

    /**
     * This listener is used to listen to the UIDefaults tables and clear out
     * the cached-precompiled map of defaults in that case.
     */
    private static final class DefaultsListener implements PropertyChangeListener {

        /**
         * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
         */
        public void propertyChange(PropertyChangeEvent evt) {
            AppContext.getAppContext().put("SeaGlassStyle.defaults", null);
        }
    }
}
