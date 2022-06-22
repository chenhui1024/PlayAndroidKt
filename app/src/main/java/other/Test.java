package other;

import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * @author ch
 * @description
 * @date 2021/12/2
 * @edit
 */
public class Test {

    public void test() {
        Type type = new TypeToken<String>(){}.getType();
    }

}
