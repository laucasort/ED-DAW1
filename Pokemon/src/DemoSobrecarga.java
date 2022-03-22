class DemoSobrecarga{

    public static void main(String[] args) {
        
        int sumaint;
        double sumadouble;

        //Llamando todas las versiones de demoSobrec
        demoSobrec();

        demoSobrec(2);

        sumaint=demoSobrec(4,6);
        System.out.println("Resultado de demoSobrec(4,6) es: "+sumaint+"\n");

        sumadouble=demoSobrec(1.1,2.2);
        System.out.println("Resultado de demoSobrec(1.1,2.2) es: "+sumadouble);

    }

    public static void demoSobrec(){
        System.out.println("Sin par·metros\n");
    }

    //Sobrecargando demoSobrec para un par√°metro int
    public static void demoSobrec(int a){
        System.out.println("Un par·metro: " +a+"\n");
    }

    //Sobrecargando demoSobrec para dos par√°metros int
    public static int demoSobrec(int a, int b){
        System.out.println("Dos par·metros: "+a+", "+b);
        return a+b;
    }

    //Sobrecargando demoSobrec para dos par√°metros double
    public static double demoSobrec(double a, double b){
        System.out.println("Dos par·metros tipo double: "+a+", "+b);
        return a+b;
    }

}