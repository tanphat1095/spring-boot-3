package vn.phat.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 *
 * @author phatle
 * @since 09/03/2024
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListObject<T> {
    private List<T> data;
    private int total;
}
