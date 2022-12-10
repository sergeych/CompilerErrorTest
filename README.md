# Show KJS comlier error

This project is used to reproduce inner compiler error:
```
java.lang.IllegalStateException: Validation failed in file StreamingJsonDecoder.kt
	at org.jetbrains.kotlin.backend.common.IrValidator.error(IrValidator.kt:86)
	at org.jetbrains.kotlin.backend.common.IrValidator.access$error(IrValidator.kt:64)
	at org.jetbrains.kotlin.backend.common.IrValidator$elementChecker$1.invoke(IrValidator.kt:90)
	at org.jetbrains.kotlin.backend.common.IrValidator$elementChecker$1.invoke(IrValidator.kt:90)
	at org.jetbrains.kotlin.backend.common.CheckIrElementVisitor.ensureBound(CheckIrElementVisitor.kt:61)
	at org.jetbrains.kotlin.backend.common.CheckIrElementVisitor.visitDeclarationReference(CheckIrElementVisitor.kt:302)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitMemberAccess(IrElementVisitorVoid.kt:230)
	at org.jetbrains.kotlin.backend.common.CheckIrElementVisitor.visitMemberAccess(CheckIrElementVisitor.kt:24)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitFunctionAccess(IrElementVisitorVoid.kt:236)
	at org.jetbrains.kotlin.backend.common.CheckIrElementVisitor.visitFunctionAccess(CheckIrElementVisitor.kt:319)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitConstructorCall(IrElementVisitorVoid.kt:241)
	at org.jetbrains.kotlin.backend.common.CheckIrElementVisitor.visitConstructorCall(CheckIrElementVisitor.kt:24)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitConstructorCall(IrElementVisitorVoid.kt:239)
	at org.jetbrains.kotlin.backend.common.CheckIrElementVisitor.visitConstructorCall(CheckIrElementVisitor.kt:24)
	at org.jetbrains.kotlin.backend.common.CheckIrElementVisitor.visitConstructorCall(CheckIrElementVisitor.kt:24)
	at org.jetbrains.kotlin.ir.expressions.IrConstructorCall.accept(IrConstructorCall.kt:27)
	at org.jetbrains.kotlin.ir.visitors.IrVisitorsKt.acceptVoid(IrVisitors.kt:11)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitElement(IrValidator.kt:93)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitExpression(IrElementVisitorVoid.kt:205)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitExpression(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitDeclarationReference(IrElementVisitorVoid.kt:224)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitDeclarationReference(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitMemberAccess(IrElementVisitorVoid.kt:230)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitMemberAccess(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitFunctionAccess(IrElementVisitorVoid.kt:236)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitFunctionAccess(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitConstructorCall(IrElementVisitorVoid.kt:241)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitConstructorCall(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitConstructorCall(IrElementVisitorVoid.kt:239)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitConstructorCall(IrValidator.kt:64)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitConstructorCall(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.expressions.IrConstructorCall.accept(IrConstructorCall.kt:27)
	at org.jetbrains.kotlin.ir.expressions.IrThrow.acceptChildren(IrThrow.kt:25)
	at org.jetbrains.kotlin.ir.visitors.IrVisitorsKt.acceptChildrenVoid(IrVisitors.kt:15)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitElement(IrValidator.kt:94)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitExpression(IrElementVisitorVoid.kt:205)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitExpression(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitThrow(IrElementVisitorVoid.kt:470)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitThrow(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitThrow(IrElementVisitorVoid.kt:468)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitThrow(IrValidator.kt:64)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitThrow(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.expressions.IrThrow.accept(IrThrow.kt:22)
	at org.jetbrains.kotlin.ir.expressions.IrContainerExpression.acceptChildren(IrContainerExpression.kt:28)
	at org.jetbrains.kotlin.ir.visitors.IrVisitorsKt.acceptChildrenVoid(IrVisitors.kt:15)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitElement(IrValidator.kt:94)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitExpression(IrElementVisitorVoid.kt:205)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitExpression(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitContainerExpression(IrElementVisitorVoid.kt:270)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitContainerExpression(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitBlock(IrElementVisitorVoid.kt:274)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitBlock(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitBlock(IrElementVisitorVoid.kt:272)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitBlock(IrValidator.kt:64)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitBlock(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.expressions.IrBlock.accept(IrBlock.kt:22)
	at org.jetbrains.kotlin.ir.expressions.IrCatch.acceptChildren(IrCatch.kt:34)
	at org.jetbrains.kotlin.ir.visitors.IrVisitorsKt.acceptChildrenVoid(IrVisitors.kt:15)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitElement(IrValidator.kt:94)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitCatch(IrElementVisitorVoid.kt:478)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitCatch(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitCatch(IrElementVisitorVoid.kt:476)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitCatch(IrValidator.kt:64)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitCatch(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.expressions.IrCatch.accept(IrCatch.kt:27)
	at org.jetbrains.kotlin.ir.expressions.IrTry.acceptChildren(IrTry.kt:31)
	at org.jetbrains.kotlin.ir.visitors.IrVisitorsKt.acceptChildrenVoid(IrVisitors.kt:15)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitElement(IrValidator.kt:94)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitExpression(IrElementVisitorVoid.kt:205)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitExpression(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitTry(IrElementVisitorVoid.kt:474)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitTry(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitTry(IrElementVisitorVoid.kt:472)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitTry(IrValidator.kt:64)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitTry(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.expressions.IrTry.accept(IrTry.kt:27)
	at org.jetbrains.kotlin.ir.expressions.IrBlockBody.acceptChildren(IrBlockBody.kt:27)
	at org.jetbrains.kotlin.ir.visitors.IrVisitorsKt.acceptChildrenVoid(IrVisitors.kt:15)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitElement(IrValidator.kt:94)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitBody(IrElementVisitorVoid.kt:209)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitBody(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitBlockBody(IrElementVisitorVoid.kt:218)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitBlockBody(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitBlockBody(IrElementVisitorVoid.kt:216)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitBlockBody(IrValidator.kt:64)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitBlockBody(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.expressions.IrBlockBody.accept(IrBlockBody.kt:24)
	at org.jetbrains.kotlin.ir.declarations.IrFunction.acceptChildren(IrFunction.kt:53)
	at org.jetbrains.kotlin.ir.visitors.IrVisitorsKt.acceptChildrenVoid(IrVisitors.kt:15)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitElement(IrValidator.kt:94)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitDeclaration(IrElementVisitorVoid.kt:104)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitDeclaration(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitFunction(IrElementVisitorVoid.kt:129)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitFunction(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitSimpleFunction(IrElementVisitorVoid.kt:175)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitSimpleFunction(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitSimpleFunction(IrElementVisitorVoid.kt:173)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitSimpleFunction(IrValidator.kt:64)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitSimpleFunction(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.declarations.IrSimpleFunction.accept(IrSimpleFunction.kt:36)
	at org.jetbrains.kotlin.ir.declarations.IrClass.acceptChildren(IrClass.kt:68)
	at org.jetbrains.kotlin.ir.visitors.IrVisitorsKt.acceptChildrenVoid(IrVisitors.kt:15)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitElement(IrValidator.kt:94)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitDeclaration(IrElementVisitorVoid.kt:104)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitDeclaration(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitClass(IrElementVisitorVoid.kt:113)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitClass(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitClass(IrElementVisitorVoid.kt:111)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitClass(IrValidator.kt:64)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitClass(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.declarations.IrClass.accept(IrClass.kt:64)
	at org.jetbrains.kotlin.ir.declarations.IrFile.acceptChildren(IrFile.kt:36)
	at org.jetbrains.kotlin.ir.visitors.IrVisitorsKt.acceptChildrenVoid(IrVisitors.kt:15)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitElement(IrValidator.kt:94)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitPackageFragment(IrElementVisitorVoid.kt:190)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitPackageFragment(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitFile(IrElementVisitorVoid.kt:200)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitFile(IrValidator.kt:71)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitFile(IrElementVisitorVoid.kt:198)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitFile(IrValidator.kt:64)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitFile(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.declarations.IrFile.accept(IrFile.kt:30)
	at org.jetbrains.kotlin.ir.declarations.IrModuleFragment.acceptChildren(IrModuleFragment.kt:47)
	at org.jetbrains.kotlin.ir.visitors.IrVisitorsKt.acceptChildrenVoid(IrVisitors.kt:15)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitElement(IrValidator.kt:94)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitModuleFragment(IrElementVisitorVoid.kt:160)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitModuleFragment(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid$DefaultImpls.visitModuleFragment(IrElementVisitorVoid.kt:158)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitModuleFragment(IrValidator.kt:64)
	at org.jetbrains.kotlin.backend.common.IrValidator.visitModuleFragment(IrValidator.kt:64)
	at org.jetbrains.kotlin.ir.declarations.IrModuleFragment.accept(IrModuleFragment.kt:41)
	at org.jetbrains.kotlin.backend.common.phaser.DumperVerifierKt.validationCallback(DumperVerifier.kt:142)
	at org.jetbrains.kotlin.backend.common.phaser.DumperVerifierKt.validationCallback$default(DumperVerifier.kt:134)
	at org.jetbrains.kotlin.ir.backend.js.JsLoweringPhasesKt$validateIrBeforeLowering$1.invoke(JsLoweringPhases.kt:121)
	at org.jetbrains.kotlin.ir.backend.js.JsLoweringPhasesKt$validateIrBeforeLowering$1.invoke(JsLoweringPhases.kt:120)
	at org.jetbrains.kotlin.ir.backend.js.JsLoweringPhasesKt$makeCustomJsModulePhase$1.invoke(JsLoweringPhases.kt:61)
	at org.jetbrains.kotlin.ir.backend.js.JsLoweringPhasesKt$makeCustomJsModulePhase$1.invoke(JsLoweringPhases.kt:53)
	at org.jetbrains.kotlin.backend.common.phaser.NamedCompilerPhase.invoke(CompilerPhase.kt:96)
	at org.jetbrains.kotlin.ir.backend.js.CompilerWithICKt.lowerPreservingTags(compilerWithIC.kt:104)
	at org.jetbrains.kotlin.ir.backend.js.CompilerKt.compileIr(compiler.kt:153)
	at org.jetbrains.kotlin.ir.backend.js.CompilerKt.compile(compiler.kt:69)
	at org.jetbrains.kotlin.ir.backend.js.CompilerKt.compile$default(compiler.kt:50)
	at org.jetbrains.kotlin.cli.js.K2JsIrCompiler.doExecute(K2JsIrCompiler.kt:385)
	at org.jetbrains.kotlin.cli.js.K2JSCompiler.doExecute(K2JSCompiler.java:183)
	at org.jetbrains.kotlin.cli.js.K2JSCompiler.doExecute(K2JSCompiler.java:72)
	at org.jetbrains.kotlin.cli.common.CLICompiler.execImpl(CLICompiler.kt:99)
	at org.jetbrains.kotlin.cli.common.CLICompiler.execImpl(CLICompiler.kt:47)
	at org.jetbrains.kotlin.cli.common.CLITool.exec(CLITool.kt:101)
	at org.jetbrains.kotlin.daemon.CompileServiceImpl.compile(CompileServiceImpl.kt:1645)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77)
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.base/java.lang.reflect.Method.invoke(Method.java:568)
	at java.rmi/sun.rmi.server.UnicastServerRef.dispatch(UnicastServerRef.java:360)
	at java.rmi/sun.rmi.transport.Transport$1.run(Transport.java:200)
	at java.rmi/sun.rmi.transport.Transport$1.run(Transport.java:197)
	at java.base/java.security.AccessController.doPrivileged(AccessController.java:712)
	at java.rmi/sun.rmi.transport.Transport.serviceCall(Transport.java:196)
	at java.rmi/sun.rmi.transport.tcp.TCPTransport.handleMessages(TCPTransport.java:587)
	at java.rmi/sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run0(TCPTransport.java:828)
	at java.rmi/sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.lambda$run$0(TCPTransport.java:705)
	at java.base/java.security.AccessController.doPrivileged(AccessController.java:399)
	at java.rmi/sun.rmi.transport.tcp.TCPTransport$ConnectionHandler.run(TCPTransport.java:704)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635)
	at java.base/java.lang.Thread.run(Thread.java:833)
```

To do it, just try to execute the only test of this project (SimpleTest.kt), it fails to compile.


