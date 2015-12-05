package bibliothek;

/**
 * @author Alexander Buyanov (806984)
 * @author Phillip Redlich (791806)
 */
public class Calculations {
    public static void main(String [] args){
        //Calculation: n(1,2,3) * 0,5;
        final Normal3 n1 = new Normal3(1 ,2 ,3);
        final Normal3 outcome1 = n1.mul(0.5);
        System.out.println("Calculation: n(1,2,3) * 0,5\n" + outcome1);

        //Calculation: n(1,2,3) + n(3,2,1)
        final Normal3 n2 = new Normal3(1 ,2 ,3);
        final Normal3 n3 = new Normal3(3 ,2 ,1);
        final Normal3 outcome2 = n2.add(n3);
        System.out.println("Calculation: n(1,2,3) + n(3,2,1)\n" + outcome2);

        //Calculation: n(1,0,0) * n(1,0,0);
        final Normal3 n4 = new Normal3(1 ,0 ,0);
        final Normal3 n41 = new Normal3(0,1,0);
        final Vector3 v1 = new Vector3(1 ,0 ,0);
        final Vector3 v11 = new Vector3(0 ,1 ,0);
        final double outcome3 = n4.dot(v1);
        final double outcome31 = n4.dot(v11);
        final double outcome4 = v1.dot(n4);
        final double outcome41 = v1.dot(n41);
        final double outcome5 = v1.dot(v1);
        final double outcome51 = v1.dot(v11);
        System.out.println("Calculation: n(1,0,0) * n(1,0,0) \n" + outcome3 + "/" + outcome31 + "/" + "/" + outcome4 + "/" + "/" + outcome41 + "/"
                            + "/" + outcome5 + "/" + outcome51 + "/\n------------");

        //Calculation: p(1,1,1) - p(2,2,0);
        final Point3 p1 = new Point3(1 ,1 ,1);
        final Point3 p2 = new Point3(2 ,2 ,0);
        final Vector3 outcome6 = p1.sub(p2);
        System.out.println("Calculation: p(1,1,1) - p(2,2,0) \n" + outcome6.toString());

        //Calculation p(1,1,1) -v(4,3,2)
        final Vector3 v2 = new Vector3(4 ,3 ,2);
        final Point3 outcome61 = p1.sub(v2);
        System.out.println("Calculation p(1,1,1) -v(4,3,2)\n" + outcome61.toString());

        //Calculation: p(1,1,1) + v(4,3,2)
        final Vector3 v21 = new Vector3(4 ,3 ,2);
        final Point3 outcome62 = p1.add(v21);
        System.out.println("Calculation: p(1,1,1) + v(4,3,2) \n" + outcome62.toString());

        //Calculation: |(1,1,1)|
        final Vector3 v3 = new Vector3(1,1,1);
        System.out.println("Calculation: |(1,1,1)|\n" + v3.magnitude + "\n------------");
        
        //Schema für Vector3 
        final Vector3 v = new Vector3(1,1,1);
        final Vector3 vtest = new Vector3(4,3,2);
        final Normal3 ntest = new Normal3(4,3,2);

        final Vector3 vsum = v.add(vtest);
        final Vector3 vsum1 = v.add(ntest);

        System.out.println("Scheme for Vector3: method add: ");
        System.out.println(vsum.toString());
        System.out.println(vsum1.toString());

        final Vector3 vsub = vtest.sub(v);
        final Vector3 nsub = vtest.sub(ntest);

        System.out.println("Scheme for Vector3: method sub: ");
        System.out.println(vsub.toString());
        System.out.println(nsub.toString());

        final Vector3 vmul = v.mul(3);

        System.out.println("Scheme for method mul: ");
        System.out.println(vmul.toString());

        //Claculation: v4 reflected on n(1,0,0)
        final Vector3 v4 = new Vector3(-0.707,0.707,0);
        final Vector3  outcome7 = v4.reflectedOn(new Normal3(0,1,0));
        System.out.println("Claculation: v reflected on n(0,1,0)\n" + outcome7.toString());

        final Vector3 v41 = new Vector3(0.707,0.707,0);
        final Vector3  outcome71 = v41.reflectedOn(new Normal3(1,0,0));
        System.out.println("Claculation: v reflected on n(1,0,0)\n" + outcome71.toString());

        final Mat3x3 mat1 = new Mat3x3(1,0,0,0,1,0,0,0,1);
        final Vector3 v5 = new Vector3(3,2,1);
        final Point3 p3 = new Point3(3,2,1);
        
        //Calculation: mat1 * v(3,2,1);
        final Vector3 outcome8 = mat1.mul(v5);
        System.out.println(outcome8.toString());
        System.out.println("Calculation: mat1 * v(3,2,1)\n" + outcome8.toString());

        //Calculation: mat1 * p(3,2,1);
        final Point3 outcome81 = mat1.mul(p3);
        System.out.println("Calculation: mat1 * p(3,2,1)\n" + outcome81.toString());

        //Calculation: mat2 * mat1;
        final Mat3x3 mat2 = new Mat3x3(1,2,3,4,5,6,7,8,9);
        final Mat3x3 outcome9 = mat2.mul(mat1);
        System.out.println("Calculation: mat2 * mat1 \n" + outcome9.toString());

        //Calculation: mat2 * mat3;
        final Mat3x3 mat3 = new Mat3x3(0,0,1,0,1,0,1,0,0);
        final Mat3x3 outcome91 = mat2.mul(mat3);
        System.out.println("Calculation: mat2 * mat3\n" + outcome91.toString());

        //Calculation changeCol;
        final Vector3 v6 = new Vector3(8,8,8);
        final Mat3x3 outcome10 = mat2.changeCol1(v6);
        System.out.println("Calculation changeCol v(8 8 8)\n" + outcome10.toString());

        final Mat3x3 outcome11 = mat2.changeCol2(v6);
        System.out.println("Calculation changeCol v(8 8 8)\n" + outcome11.toString());

        final Mat3x3 outcome111 = mat2.changeCol3(v6);
        System.out.println("Calculation changeCol v(8 8 8)\n" + outcome111.toString());
    }
}
