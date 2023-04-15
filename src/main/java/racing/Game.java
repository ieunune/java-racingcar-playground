package racing;

public class Game {


    public boolean nameValidation(String cars) {

        if (!cars.contains("\\,")) {
            System.out.println("경주할 자동차 이름은 2개 이상이 필요하고 구분은 쉼표(,)로 해주셔야 합니다.");
            return false;
        }

        return true;
    }
}
