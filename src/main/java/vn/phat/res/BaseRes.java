package vn.phat.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BaseRes<T> {
    private Long took;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<T> datas;

    private List<String> errorMessage;

    public BaseRes(){
        super();
    }

    public BaseRes(T data, long current){
        super();
        this.data = data;
        this.took = getTook(current);
    }

    public BaseRes(List<T> datas, long current){
        super();
        this.datas = datas;
        this.took = getTook(current);
    }

    private long getTook(long took){
        long current = System.currentTimeMillis();
        return current - took;
    }
}
