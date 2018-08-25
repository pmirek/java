import java.lang.management.ManagementFactory;
import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.management.ObjectName;
import javax.management.Attribute;

public class MbeanSet {


 public static MBeanServerConnection serverConn;


 public static void main(String[] args) {
  System.out.println("Setting [" + args[1] + "] for [" + args[0] + "]");
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
     String[] attribute = data[j].split(":");
     String attributeName = attribute[0];
     String attributeValue = attribute[1];
     System.out.println("Setting ["+ objNameStr + "|" + attributeName + "]: " + attributeValue);
     serverConn.setAttribute(objName, new Attribute(attributeName,attributeValue));
     //System.out.println(objNameStr + "|" + data[j] + ": " + serverConn.getAttribute(objName, attributeName).toString());
    }
   }
  } catch (Exception e) { 
   System.out.println(e);
  }

 }

}


