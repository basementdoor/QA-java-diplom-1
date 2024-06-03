package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;
import static praktikum.utils.Utils.faker;
import static praktikum.constants.Constants.DELTA;

@RunWith(Parameterized.class)
public class IngredientParamTest {

    protected Ingredient ingredient;
    private IngredientType type;
    private String name;
    private float price;

    public IngredientParamTest(IngredientType type, String name, float price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {IngredientType.SAUCE, faker.funnyName().name(), faker.number().numberBetween(0, (long) 999.9)},
                {IngredientType.FILLING, faker.funnyName().name(), faker.number().numberBetween(0, (long) 999.9)},
                {IngredientType.SAUCE, faker.funnyName().name(), faker.number().numberBetween(0, (long) 999.9)},
        };
    }

    @Before
    public void setIngredient() {
        ingredient = new Ingredient(type, name, price);
    }

    @Test
    public void getIngredientPriceTest() {
        float actualPrice = ingredient.getPrice();

        assertEquals("Ожидалась другая цена инредиента", price, actualPrice, DELTA);
    }

    @Test
    public void getIngredientNameTest() {
        String actualName = ingredient.getName();

        assertEquals("Ожидалось другое название инредиента", name, actualName);
    }

    @Test
    public void getIngredientTypeTest() {
        IngredientType actualType = ingredient.getType();

        assertEquals("Ожидался другой тип инредиента", type, actualType);
    }


}
