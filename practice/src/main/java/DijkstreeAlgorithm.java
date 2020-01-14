import java.util.*;

public class DijkstreeAlgorithm {
  public static void main(String[] args) {
    Map<Integer, List<Point>> graph = new HashMap<Integer, List<Point>>(); //Создаем граф где ключ будет стоимотью перехода к другой вершине

    //Созлаем вершини

    Point start = new Point("Start");
    Point a = new Point("A");
    Point b = new Point("B");
    Point end = new Point("End");

    //Создаем имитацию напрвленных ребер

    List<Point> start_a = new ArrayList<>();
    start_a.add(start);
    start_a.add(a);

    List<Point> start_b = new ArrayList<>();
    start_b.add(start);
    start_b.add(b);

    List<Point> b_a = new ArrayList<>();
    b_a.add(b);
    b_a.add(a);

    List<Point> a_end = new ArrayList<>();
    a_end.add(a);
    a_end.add(end);

    List<Point> b_end = new ArrayList<>();
    b_end.add(b);
    b_end.add(end);

    List<Point> start_end = new ArrayList<>();
    start_end.add(start);
    start_end.add(end);

    // заполняем граф стоимостью перехода и ребрами

    graph.put(6, start_a);
    graph.put(3, start_b);
    graph.put(4, b_a);
    graph.put(1, a_end);
    graph.put(2, start_end);
    graph.put(5, b_end);

    //Вызываем метод реализующий алгоритм Дейкстри

    System.out.println(dijkstreeMethod(graph, start, end));
  }

  private static long dijkstreeMethod(Map<Integer, List<Point>> graph, Point start, Point end) {
    int distance = 0;   //Общая цена перехода с одних ребер на другие (самый лучший вариант пути с точки start в точку end)
    int basicValue = 0; //Вспомагательное значение для нахождения на каждой вершине оптимального пути

    if (start.equals(end)) return distance;  //Проверка на необходимость выполнения алгоритма

    do { //Цикл выполняется пока точка start и точка end не равны

      for (Map.Entry<Integer, List<Point>> point : graph.entrySet()) { //Проход по всему графу

        if (basicValue > point.getKey() | basicValue == 0) { // Выполнением алгоритм при условии что усть лучше вариант прохода к вершине или если еще не искали его
          if (point.getValue().contains(start)) {            // Проверяем ребро на наличие вершины
            if (basicValue == 0) {                           // Если еще не находили
              basicValue = point.getKey();                   // Присваем первый вариант ребра
              distance += basicValue;                        //
              for (Point p : point.getValue()) {             // проходим по вершинам которые соеденяет
                if (!start.equals(p))                        // ребро и присваем нчальной точке новую вершину
                  start = p;                                 //
              }                                              //

            } else if (basicValue > point.getKey()) {        // Если есть вариан лучше для перехода на новую вершину
              distance -= basicValue;                        // убираем предыдущее значение из финального результата прохода
              distance += point.getKey();                    // добавляем ему новое

              for (Point p : point.getValue()) {             // проходим по вершинам которые соеденяет
                if (!start.equals(p))                        // ребро и присваем нчальной точке новую вершину
                  start = p;
              }

            }

          }
        }

      }
      System.out.println(graph.remove(basicValue).toString()); // после прохода и выбора подходящего пути мы его удаляем
      basicValue = 0;                                          // обнуляемся чтобы не было зацикливания

    } while (!start.equals(end));

    return distance;
  }

}
