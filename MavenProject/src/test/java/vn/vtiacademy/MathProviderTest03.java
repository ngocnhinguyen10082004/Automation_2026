package vn.vtiacademy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class MathProviderTest03 {

  private static final Logger LOGGER = LoggerFactory.getLogger(MathProviderTest03.class.getSimpleName());
  static MathProvider mathProvider;

  @BeforeAll
  public static void beforeAll() {
    LOGGER.info("====================Starting All Tests===================");
    mathProvider = new MathProvider();
  }


  @ParameterizedTest
  @ValueSource(ints = {1, 2, 3})
  @DisplayName("MP001: Add two number successfully")
  public void MP001_add_two_numbers_successfully(int number) {
    // 3A = Arrange, Act, Assert
    // Arrange = Pre-condition
    LOGGER.info("MP001");
//    MathProvider mathProvider = new MathProvider();
    // Act = Test steps
    int actualSum = mathProvider.add(number, number);

    // Assert = Expected Result
    assertEquals(number * 2, actualSum);
  }

  @ParameterizedTest
  @CsvSource({"10, 20, -10", "23, 34, -11"})
  @DisplayName("MP002: Subtract two number successfully")
  public void MP002_subtract_two_numbers_successfully(int firstNumber, int secondNumber, int result) {
    LOGGER.info("MP002");
//    MathProvider mathProvider = new MathProvider();
    int actualSub = mathProvider.subtract(firstNumber, secondNumber);
    assertEquals(result, actualSub);
  }

  @ParameterizedTest
  @CsvFileSource(files = "src/test/resources/data/MP003.csv", numLinesToSkip = 1)
  @DisplayName("MP003: Multiply two number successfully")
  public void MP003_multiply_two_numbers_successfully(int firstNumber, int secondNumber, int result) {
    LOGGER.info("MP003");
//    MathProvider mathProvider = new MathProvider();
    int actualMul = mathProvider.multiply(firstNumber, secondNumber);
    assertEquals(result, actualMul);
  }

  @AfterAll
  public static void afterAll() {
    LOGGER.info("====================Ended All Tests===================");
  }
}
