## .NET Common Language Runtime (CLR)

.NET CLR is a runtime environment that manages and executes the code written in any .NET programming language. CLR is the virtual machine component of the .NET framework. That language's compiler compiles the source code of applications developed using .NET compliant languages into CLR's intermediate language called MSIL, i.e., Microsoft intermediate language code. This code is platform-independent. It is comparable to byte code in java.

![](https://static.javatpoint.com/csharp/net/images/net-common-language-runtime2.png)


ref: https://www.javatpoint.com/net-common-language-runtime

## Just in Time (JIT) Compiler:
JIT Compiler is an important component of CLR. It converts the MSIL code into native code (i.e., machine-specific code). The .NET program is compiled either explicitly or implicitly. The developer or programmer calls a particular compiler to compile the program in the explicit compilation. In implicit compilation, the program is compiled twice. The source code is compiled into Microsoft Intermediate Language (MSIL) during the first compilation process. The MSIL code is converted into native code in the second compilation process. This process is called JIT compilation. 

There are three types of JIT compilers -Pre, Econo, and Normal.

## Global Assembly Cache (GAC)
The Global Assembly Cache (GAC) is a feature in the .NET Framework that serves as a centralized repository for storing assemblies that need to be shared by multiple applications on a system. Here are the primary purposes and benefits of the GAC:

### Sharing Assemblies
The GAC allows different applications on the same machine to share common assemblies rather than having each application store its own copy. This not only saves disk space but also ensures that all applications are using the same version of an assembly, which can help maintain consistency and compatibility across applications.

## MCQs
[https://www.includehelp.com/mcq/asp-net-mcqs.aspx](https://www.includehelp.com/mcq/csharp-multiple-choice-questions-mcqs.aspx)
