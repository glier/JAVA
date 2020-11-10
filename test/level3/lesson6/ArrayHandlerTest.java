package level3.lesson6;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import com.geekbrains.level3.lesson6.ArrayHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class ArrayHandlerTest {
    private ArrayHandler arrayHandler = null;

    @ParameterizedTest
    @MethodSource("data")
    public void arrSplitTest(int[] arr, int[] resArr) {
        try {
            assertArrayEquals(resArr, arrayHandler.subArray(arr));
        } catch (Exception e) {
            Assertions.assertThrows(RuntimeException.class, () -> arrayHandler.subArray(arr));
        }
    }

    static Stream<Arguments> data() {
        return Stream.of(
                arguments(new int[]{8, 7, 5, 6, 2, 4, 3, 1}, new int[]{3, 1}),
                arguments(new int[]{8, 7, 4, 6, 2, 0, 3, 1}, new int[]{6, 2, 0, 3, 1}),
                arguments(new int[]{8, 7, 0, 6, 2, 0, 3, 0}, new int[]{})
        );
    }

    @ParameterizedTest
    @MethodSource("data2")
    public void arrIsExistsOneAndFourOnlyTest(int[] arr) {
        Assertions.assertTrue(arrayHandler.arrIsExistsOneAndFourOnly(arr));
    }

    static Stream<Arguments> data2() {
        return Stream.of(
                arguments(new int[]{1, 1, 1, 4, 4, 1, 4, 4}),
                arguments(new int[]{1, 1, 1, 1, 1, 1}),
                arguments(new int[]{4, 4, 4, 4}),
                arguments(new int[]{1, 4, 4, 1, 1, 4, 3})
        );
    }

    @BeforeEach
    public void init() {
        arrayHandler = new ArrayHandler();
    }

    @AfterEach
    public void tearDown() {
        arrayHandler = new ArrayHandler();
    }
}
