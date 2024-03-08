package vn.phat.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 *
 * @author phatle
 * @since 09/03/2024
 */
@Getter
@Setter
public class BaseRes<T> {
    private Long took;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    private List<String> errorMessage;

    public BaseRes() {
        super();
    }

    public BaseRes(T data, long current) {
        super();
        this.data = data;
        this.took = getTook(current);
    }

    private long getTook(long took) {
        long current = System.currentTimeMillis();
        return current - took;
    }
}
