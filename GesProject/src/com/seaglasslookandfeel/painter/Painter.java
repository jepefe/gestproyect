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
 * $Id: Painter.java 466 2009-12-06 19:21:29Z kathryn@kathrynhuxtable.org $
 */
package com.seaglasslookandfeel.painter;

import java.awt.Graphics2D;

/**
 * An interface that allows painting to be delegated. The implementation of this
 * interface will be called during the painting process of the given {@code
 * objectToPaint}.
 */
public interface Painter<T> extends com.sun.java.swing.Painter<T> {

    /**
     * Renders to the given {@link Graphics2D}. The supplied graphics context
     * may be modified - it's state need not be restored upon completion of
     * painting.
     * 
     * @param graphics
     *            the graphics context to paint into. It's state need not be
     *            restored. Will not be null.
     * @param objectToPaint
     *            the object to be painted.
     * @param width
     *            the width within the object to paint.
     * @param height
     *            the height within the object to paint.
     */
    void paint(Graphics2D graphics, T objectToPaint, int width, int height);

}
