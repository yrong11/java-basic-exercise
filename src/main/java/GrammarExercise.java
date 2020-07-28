import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GrammarExercise {
    public static void main(String[] args) {
        //需要从命令行读入
        String firstWordList = args[0];
        String secondWordList = args[1];

        List<String> result = findCommonWordsWithSpace(firstWordList,secondWordList);
        //按要求输出到命令行
        result.forEach(System.out::println);

    }

    public static List<String> findCommonWordsWithSpace(String firstWordList, String secondWordList) {
        //在这编写实现代码
        List<String> firstList = Arrays.stream(firstWordList.split(","))
                .map(String::toUpperCase)
                .distinct()
                .collect(Collectors.toList());
        List<String> secondList = Arrays.stream(secondWordList.split(","))
                .map(String::toUpperCase)
                .distinct()
                .collect(Collectors.toList());

        String regex = "[A-Z]+";
        if (firstList.stream().filter(str -> str.matches(regex)).count() < firstList.size() ||
            secondList.stream().filter(str -> str.matches(regex)).count() < secondList.size()) {
            throw new RuntimeException("input not valid");
        }

        List<String> results = firstList.stream().filter(str -> secondList.contains(str))
                .sorted()
                .map(str -> str.replace("", " ").trim())
                .collect(Collectors.toList());

        return results;
    }


}
