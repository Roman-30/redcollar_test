package shevt.game.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public abstract class Node implements Serializable {
    @Serial
    private static final long serialVersionUID = 4L;
    private final String label;
}
