import java.lang.management.ManagementFactory;
import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.management.ObjectName;

public class MbeanGet {


 public static MBeanServerConnection serverConn;


 public static void main(String[] args) {
  System.out.println("Getting [" + args[1] + "] from [" + args[0] + "]");
  try {
   //connect to a remote VM using JMX RMI
   JMXServiceURL url = new JMXServiceURL( "service:jmx:rmi:///jndi/rmi://"+args[0]+"/jmxrmi");
   JMXConnector jmxConnector = JMXConnectorFactory.connect(url);
   serverConn = jmxConnector.getMBeanServerConnection();
   String[] mbeans = args[1].split(";");
   for (int i = 0; i <= mbeans.length - 1; i++){
    String[] data = mbeans[i].split("\\|");
    String objNameStr=data[0];
    ObjectName objName = new ObjectName(objNameStr);
    for (int j = 1; j <= data.length - 1; j++){
     //String attributeValue =  (String) serverConn.getAttribute(objName, data[j]).toString();
     String attributeValue = serverConn.getAttribute(objName, data[j]).toString();
     System.out.println(objName + "|" + data[j] + ": " + attributeValue);
    }
   }
  } catch (Exception e) { 
   System.out.println(e);
  }

 }

}


