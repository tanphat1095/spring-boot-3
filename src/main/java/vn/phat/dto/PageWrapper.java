package vn.phat.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageWrapper<D> {
    private List<D> datas;
    private int total;

    public PageWrapper(List<D> datas, int total) {
        this.datas = datas;
        this.total = total;
    }

    public PageWrapper() {
        super();
    }

}
