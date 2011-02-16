package pkGesproject.Avisos;

class ExceptionManager{

    public static void ManageException (Exception e)
    {

        System.out.println ("Se ha producido una exception");

        System.out.println (e.getMessage());

        e.printStackTrace(System.out);

    }

}