package org.vege.model;

import java.util.List;

/**
 * Created by rustbell on 5/20/17.
 */
public class VegeListResp {
    private Integer total;
    private List<Vege> rows;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<Vege> getRows() {
        return rows;
    }

    public void setRows(List<Vege> rows) {
        this.rows = rows;
    }
}
