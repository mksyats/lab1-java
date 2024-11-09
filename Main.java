public class Main {

  public static void main(String[] args) {
    final float[][] A = {
        {-10.7f, -8f, -3.21f},
        {0.531f, 21.2f, 0f},
        {81.9f, 0.7f, 100.3f},
        {-9f, 15.4f, 64.05f},
        {54.9f, 12.3f, 13.3f},
    };
    final float[][] B = {
        {25.67f, 88.5f, -25.6f},
        {48.44f, -3.4f, 168.01f},
        {0f, -99.26f, 0.98f},
        {-26.56f, 0.81f, 6.29f},
        {68.84f, 73.2f, -97.4f},
    };

    try {
      validateMatrices(A, B);
    } catch (IllegalArgumentException e) {
      System.err.println("Неправильні вхідні дані: " + e.getMessage() + ".");
      System.exit(1);
    }

    float[][] C = addMatrices(A, B);
    System.out.println("Завдання 1. Матриця C = A + B:");
    printMatrix(C);

    float sum = calcElemsSum(C);
    System.out.println("Завдання 2. Сума заданих елементів матриці C: " + sum);
  }

  public static float[][] addMatrices(float[][] matrix1, float[][] matrix2) {
    int rows = matrix1.length;
    int cols = matrix1[0].length;

    float[][] res = new float[rows][cols];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        res[i][j] = matrix1[i][j] + matrix2[i][j];
      }
    }

    return res;
  }

  public static float calcElemsSum(float[][] matrix) {
    float sum = 0;

    for (int i = 0; i < matrix.length; i++) {
      float extreme = matrix[i][0];
      for (int j = 1; j < matrix[i].length; j++) {
        float elem = matrix[i][j];
        extreme = ((i + 1) % 2 == 0) ? Math.min(extreme, elem) : Math.max(extreme, elem);
      }
      sum += extreme;
    }

    return sum;
  }

  public static void validateMatrices(float[][] matrix1, float[][] matrix2) {
    if (matrix1 == null || matrix2 == null) {
      throw new IllegalArgumentException("матриця не може дорівнювати null");
    }

    int rows = matrix1.length;
    int cols = matrix1[0].length;

    if (cols == 0) {
      throw new IllegalArgumentException("матриця повинна мати принаймні 1 стовпець");
    }

    if (matrix2.length != rows) {
      throw new IllegalArgumentException(
          "матриця 2 повинна мати таку ж кількість рядків, що й матриця 1");
    }

    for (int i = 0; i < rows; i++) {
      if (matrix1[i].length != cols) {
        throw new IllegalArgumentException(
            "кожен рядок матриці повинен мати однакову кількість стовпців");
      }

      if (matrix2[i].length != matrix1[i].length) {
        throw new IllegalArgumentException("матриці 1 та 2 повинні мати однакові розміри");
      }
    }
  }

  public static void printMatrix(float[][] matrix) {
    for (float[] row : matrix) {
      System.out.print("\t");
      for (float elem : row) {
        System.out.printf("%8.3f\t", elem);
      }
      System.out.println();
    }
  }
}
