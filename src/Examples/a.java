package Examples;

public class a {

    public  static void main(String args[]){
       double vidstan;
       int b = 5600;
       vidstan = 10.4 * 1100 / 2;

       System.out.println("Відстань до скали" + " " + vidstan + " - " +"футів");

       int[]  a = new int[] {1, 2, 2, 3, 4};
       System.out.println(a[1]);

       if(b < 5500) System.out.println("Менше");
       if(b > 5600) System.out.println("Більше");
    //    else if(b != 56500) System.out.println("Не рівні");



               int  p;
               int  q;
                System.out.println("P\tQ\tAND\tOR\tXOR\tNOT");
                p = 1 ; q = 1;
                System.out.print(p + "\t" + q +"\t");
                System.out.print((p & q) + "\t" + (p|q) + "\t");
                System.out.println((p ^ q) + "\t" + (p));
                p = 1; q = 0;
                System.out.print(p + "\t" + q +"\t");
                System.out.print((p&q) + "\t" + (p|q) + "\t");
                System.out.println((p ^ q) + "\t" + (p));
                p = 0; q = 1;
                System.out.print(p + "\t" + q +"\t");
                System.out.print((p&q) + "\t" + (p|q) + "\t");
                System.out.println( (p ^ q) + "\t" + (p));
                p = 0; q = 0;
                System.out.print(p + "\t" + q +"\t");
                System.out.print((p&q) + "\t" + (p|q) + "\t");
                System.out.println((p ^ q) + "\t" + (p));

                int ac = 2;
                int ad = 100;
                if(ac < ad){ System.out.println(ad / ac);

        }



        }
    }

