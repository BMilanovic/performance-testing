//package test.bmilan.bean.measurement;
//
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.asset.EmptyAsset;
//import org.jboss.shrinkwrap.api.spec.JavaArchive;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import javax.ejb.EJB;
//import java.util.List;
//
//import static junit.framework.TestCase.assertNotNull;
//
//@RunWith(Arquillian.class)
//public class MeasurementECTest
//{
//
//    @Deployment
//    public static JavaArchive createDeployment()
//    {
//        JavaArchive jar = ShrinkWrap.create(JavaArchive.class)
//                .addClass(Measurement.class)
//                .addClass(AnalyticsBean.class)
//                .addClass(AnalyticsSeries.class)
//                .addClass(BasicMeasurement.class)
//                .addClass(MeasurementFactory.class)
//                .addClass(SimpleMeasurement.class)
//                .addClass(RandomMeasurement.class)
//                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
//        System.out.println(jar.toString(true));
//        return jar;
//    }
//
//    @EJB
//    MeasurementFactory factory;
//
//    @Test
//    public void testSimple()
//    {
//        Measurement simpleMeasurement = factory.createSimpleMeasurement(10, 10, 10, 10);
//
//        simpleMeasurement.runAll();
//        List<AnalyticsSeries> measurements = simpleMeasurement.getMeasurements();
//
//        assertNotNull(measurements);
//    }
//
//}
