package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static praktikum.utils.Utils.faker;
import static praktikum.constants.Constants.DELTA;
import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunParamTest {
    protected Bun bun;
    private String inputBunName;
    private float inputBunPrice;

    public BunParamTest(String bunName, float bunPrice) {
        this.inputBunName = bunName;
        this.inputBunPrice = bunPrice;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {faker.funnyName().name(), faker.number().numberBetween(0, (long) 999.9)},
                {faker.funnyName().name(), faker.number().numberBetween(0, (long) 999.9)},
                {faker.funnyName().name(), faker.number().numberBetween(0, (long) 999.9)},
        };
    }

    @Before
    public void setBun() {
        bun = new Bun(inputBunName, inputBunPrice);
    }

    @Test
    public void getBunNameTest() {
        String actualBunName = bun.getName();
        assertEquals("Ожидалось другое название булочки", inputBunName, actualBunName);
    }

    @Test
    public void getBunPriceTest() {
        float actualBunPrice = bun.getPrice();
        assertEquals("Ожидалась другая стоимость булочки", inputBunPrice, actualBunPrice, DELTA);
    }


}
