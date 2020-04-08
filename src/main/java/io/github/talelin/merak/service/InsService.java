package io.github.talelin.merak.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.talelin.merak.dto.book.CreateOrUpdateBookDTO;
import io.github.talelin.merak.dto.ins.InsAddDTO;
import io.github.talelin.merak.dto.ins.InsUpdateDTO;
import io.github.talelin.merak.dto.resident.FamilyEditDTO;
import io.github.talelin.merak.dto.resident.UpdateResidentDTO;
import io.github.talelin.merak.dto.user.RegisterDTO;
import io.github.talelin.merak.model.BookDO;
import io.github.talelin.merak.model.InsDO;
import io.github.talelin.merak.model.ResidentDO;
import io.github.talelin.merak.vo.InsVO;
import io.github.talelin.merak.vo.ResidentFamilyVO;
import io.github.talelin.merak.vo.ResidentVO;

import java.util.List;

/**
 * 用户业务
 *
 * @author pedro
 * @since 2019-11-30
 */
public interface InsService extends IService<InsDO> {

    boolean createIns(InsAddDTO dto);

    List<InsVO> findAll();

    boolean deleteById(int id);


    boolean updateIns(InsDO ins, InsUpdateDTO dto);

}
