package org.vege.service;

import org.springframework.stereotype.Service;
import org.vege.dao.VegeDao;
import org.vege.model.Vege;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by rustbell on 5/20/17.
 */
@Service
public class VegeService {
    @Resource
    VegeDao vegeDao;

    public Vege getById(Long id)
    {
        return vegeDao.getByID(id);
    }

    public List<Vege> getBySellerId(Long sellerId)
    {
        return vegeDao.getBySellerId(sellerId);
    }

    public List<Vege> getAll()
    {
        return vegeDao.getAll();
    }

    public int add (Vege vege) { return vegeDao.add(vege); }

    public int removeById (Long id) { return vegeDao.remove(id); }

    public int modify(Vege vege, Long vid) { return vegeDao.modify(vege, vid); }
}
