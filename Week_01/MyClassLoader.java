import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoader extends ClassLoader {
  public static void main(String[] args) {
    try {
      MyClassLoader loader = new MyClassLoader();
      Class<?> aClass = loader.findClass("Hello");
      Object object = aClass.newInstance();
      Method method = aClass.getMethod("hello");
      method.invoke(object);
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InstantiationException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
  }
  @Override
  protected Class<?> findClass(String name) {
    try {
      byte[] bytes = getByteArray();
      return defineClass(name, bytes, 0, bytes.length);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  public byte[] getByteArray() throws IOException {
      InputStream is = new FileInputStream("Hello.xlass");
      int num = is.available();
      byte[] bytes = new byte[num];
      is.read(bytes);
      is.close();
      for (int i = 0; i < bytes.length; i++) {
        bytes[i] = (byte)(255 - bytes[i]);
      }
      return bytes;
  }
}
