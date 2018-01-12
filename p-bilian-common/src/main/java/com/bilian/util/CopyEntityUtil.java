package com.bilian.util;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CopyEntityUtil<T_PO, T_VO> {

    private Class<T_PO> targetPo;

    private Class<T_VO> targetVo;

    public CopyEntityUtil(Class<T_PO> targetPo, Class<T_VO> targetVo) {
        this.targetPo = targetPo;
        this.targetVo = targetVo;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public T_VO po2vo(T_PO po) {
        if (po != null) {
            T_VO vo = BeanUtils.instantiateClass(targetVo);
            BeanUtils.copyProperties(po, vo);
            return vo;
        } else {
            return null;
        }
    }

    public List<T_VO> po2vo(List<T_PO> pos) {
        if (pos != null && !pos.isEmpty()) {
            List<T_VO> pojos = new ArrayList<>();
            for (T_PO po : pos) {
                pojos.add(po2vo(po));
            }
            return pojos;
        } else {
            return null;
        }
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public T_PO vo2po(T_VO vo) {
        if (vo != null) {
            T_PO po = BeanUtils.instantiateClass(targetPo);
            BeanUtils.copyProperties(vo, po);
            return po;
        } else {
            return null;
        }
    }

    public List<T_PO> vo2po(List<T_VO> vos) {
        if (vos != null && !vos.isEmpty()) {
            List<T_PO> pos = new ArrayList<>();
            for (T_VO vo : vos) {
                pos.add(vo2po(vo));
            }
            return pos;
        } else {
            return null;
        }
    }

    public List<T_VO> po2vo(Set<T_PO> vos) {
        return po2vo(new ArrayList(vos));
    }

    public List<T_PO> vo2po(Set<T_VO> vos) {
        return vo2po(new ArrayList(vos));
    }

}
