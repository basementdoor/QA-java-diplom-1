package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static praktikum.constants.Constants.*;

public class IngredientTypeTest {

    @Test
    public void ingredientTypeSauceTest() {
        assertEquals("Имя типа ингредиента не соответствует ожидаемому",
                SAUCE, IngredientType.SAUCE.name());
    }

    @Test
    public void ingredientTypeFillingTest() {
        assertEquals("Имя типа ингредиента не соответствует ожидаемому",
                FILLING, IngredientType.FILLING.name());
    }

    @Test
    public void ingredientTypeLengthTest() {
        assertEquals("Длина списка типов не соответствует ожидаемому",
                ENUM_LENGTH, IngredientType.values().length);
    }
}
