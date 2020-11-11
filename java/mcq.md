# 1

What will be the output of the following program?
```
    public class MyFirst {  
          public static void main(String[] args) {  
             MyFirst obj = new MyFirst(n);  
     }  
     static int a = 10;  
     static int n;  
     int b = 5;  
     int c;  
     public MyFirst(int m) {  
           System.out.println(a + ", " + b + ", " + c + ", " + n + ", " + m);  
       }  
    // Instance Block  
      {  
         b = 30;  
         n = 20;  
      }   
    // Static Block  
      static   
    {  
              a = 60;  
         }   
     }  
```

Answer: 60, 30, 0, 20, 0

Explanation: In the above code, there are two values of variable a, i.e., 10 and 60. Similarly, there are two values of variable b, i.e., 5 and 30. But in the output, the values of a and b are 60 and 30, respectively. It is because of the execution order of the program.

The execution order of the program is that the static block executes first, then instance block, and then constructor. Hence, the JVM will consider the value of a and b as 60 and 30 concerning the execution order. The value of a = 10 and b = 5 are of no use. And the value of variables c and m is 0 as we have not assigned any value to them.

Hence, the correct answer is an option (d).
