package am.adrian.dungeonkeeper.common.health;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Health {

    private int level;

    public void increase(int amount) {
        level += amount;
    }

    public void decrease(int amount) {
        level -= amount;
    }
}
