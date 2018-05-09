//package xmlGenerator;
//
//import com.sun.codemodel.JCodeModel;
//import com.sun.tools.xjc.api.S2JJAXBModel;
//import com.sun.tools.xjc.api.SchemaCompiler;
//import com.sun.tools.xjc.api.XJC;
//import org.xml.sax.InputSource;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.PrintStream;
//
//
//public class EnumGenerators {
//
//    /**
//     * Generates Java classes in targetPath directory given an XML schema.
//     *
//     * @param schemaFile file reference to the XML schema
//     * @param packageName package name for generated model classes
//     * @param targetPath directory where class source will be generated
//     * @return the generated code model
//     * @throws Exception failure during model generation
//     */
//    public JCodeModel generateFromSchema(final File schemaFile, final String packageName,
//                                         final File targetPath) throws Exception {
//
//        final SchemaCompiler sc = XJC.createSchemaCompiler();
//        final FileInputStream schemaStream = new FileInputStream(schemaFile);
//        final InputSource is = new InputSource(schemaStream);
//        is.setSystemId(schemaFile.getAbsolutePath());
//
//        sc.parseSchema(is);
//        sc.forcePackageName(packageName);
//
//        final S2JJAXBModel s2 = sc.bind();
//        final JCodeModel jcm = s2.generateCode(null, null);
//        try (PrintStream status = new PrintStream(new ByteArrayOutputStream())) {
//            jcm.build(targetPath, status);
//        }
//
//        return jcm;
//    }
//
//    public static void main(String[] args) {
//
//    }
//
//}
