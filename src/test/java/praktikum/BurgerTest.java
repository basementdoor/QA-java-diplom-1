package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static praktikum.constants.Constants.DELTA;
import static org.junit.Assert.*;
import static praktikum.utils.Utils.faker;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    protected Burger burger;
    @Mock
    Bun bunMock;
    @Mock
    Ingredient ingredientMock1, ingredientMock2;

    @Before
    public void setBurger() {
        burger = new Burger();
    }

    @Test
    public void setBunsTest() {
        burger.setBuns(bunMock);
        assertEquals(bunMock, burger.bun);
    }

    @Test
    public void addIngredientTest() {
        burger.addIngredient(ingredientMock1);
        assertTrue("В списке отсутствует нужный игредиент", burger.ingredients.contains(ingredientMock1));
    }

    @Test
    public void removeIngredientTest() {
        int expectedListSize = 1;
        int index = 0;
        burger.addIngredient(ingredientMock1);
        assertTrue("В списке отстутствует нужный игрединет", burger.ingredients.contains(ingredientMock1));
        assertEquals("В списке больше одного элемента", expectedListSize, burger.ingredients.size());
        // часть теста, что выше - чтобы убедиться, что в списке есть игредиент
        burger.removeIngredient(index);
        assertFalse("Ингредиент не был удален", burger.ingredients.contains(ingredientMock1));
    }

    @Test
    public void moveIngredinetTest() {
        int expectedListSize = 2;
        burger.ingredients.add(ingredientMock1);
        burger.ingredients.add(ingredientMock2);
        assertEquals("Ингредиенты не были добавлены", expectedListSize, burger.ingredients.size());
        // часть теста, что выше - добавляем игредиенты, проверяем это
        burger.moveIngredient(0, 1);
        assertEquals("Ожидался ingredientMock1", ingredientMock1, burger.ingredients.get(1));
        assertEquals("Ожидался ingredientMock2", ingredientMock2, burger.ingredients.get(0));
    }

    @Test
    public void getPriceTest() {
        float bunPrice = faker.number().numberBetween(0, (long) 999.9);
        float ingredinetPrice = faker.number().numberBetween(0, (long) 999.9);
        float expectedBurgerPrice = bunPrice * 2 + ingredinetPrice;
        Mockito.when(bunMock.getPrice()).thenReturn(bunPrice);
        Mockito.when(ingredientMock1.getPrice()).thenReturn(ingredinetPrice);
        // добавили игредиент, проверили, что добавился
        burger.setBuns(bunMock);
        burger.ingredients.add(ingredientMock1);
        assertTrue("Ингредиент не добавился", burger.ingredients.contains(ingredientMock1));
        // основная проверка
        float actualBurgerPrice = burger.getPrice();
        assertEquals("Ожидалась другая цена бургера", expectedBurgerPrice, actualBurgerPrice, DELTA);
    }

    @Test
    public void getReceiptTest() {
        float bunPrice = faker.number().numberBetween(0, (long) 999.9);
        String bunName = faker.funnyName().name();
        float ingredientPrice = faker.number().numberBetween(0, (long) 999.9);
        String ingredientName = faker.funnyName().name();
        float expectedBurgerPrice = (bunPrice * 2) + ingredientPrice;
        burger.setBuns(bunMock);
        burger.addIngredient(ingredientMock1);
        String expectedReceipt = String.format("(==== %s ====)%n", bunName) + String.format("= %s %s =%n", IngredientType.SAUCE.toString().toLowerCase(), ingredientName) +
                String.format("(==== %s ====)%n", bunName) +
                String.format("%nPrice: %f%n", expectedBurgerPrice);
        Mockito.when(bunMock.getName()).thenReturn(bunName);
        Mockito.when(bunMock.getPrice()).thenReturn(bunPrice);
        Mockito.when(ingredientMock1.getName()).thenReturn(ingredientName);
        Mockito.when(ingredientMock1.getPrice()).thenReturn(ingredientPrice);
        Mockito.when(ingredientMock1.getType()).thenReturn(IngredientType.SAUCE);
        assertEquals("Чеки отличаются", expectedReceipt, burger.getReceipt());
    }
}
