package uz.authorizationapp.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import uz.authorizationapp.entity.Lists;
import uz.authorizationapp.repository.ListRepository;
import uz.authorizationapp.upload.ApiResponse;
import uz.authorizationapp.upload.ListDto;

@Data
@AllArgsConstructor
@Service
public class ListService {
    private final  ListRepository listRepository;
    public ApiResponse createList(ListDto lists){
        Lists lists1 = new Lists();
        lists1.setId(lists.getId());
        lists1.setType_id(lists.getType_id());
        lists1.setName_uz(lists.getName_uz());
        lists1.setName_ru(lists.getName_ru());
        lists1.setName_la(lists.getName_la());
        lists1.setName_en(lists.getName_en());
        lists1.setInt01(lists.getInt01());
        lists1.setInt02(lists.getInt02());
        lists1.setInt03(lists.getInt03());
        lists1.setInt04(lists.getInt04());
        lists1.setVal01(lists.getVal01());
        lists1.setVal02(lists.getVal02());
        lists1.setVal03(lists.getVal03());
        lists1.setVal04(lists.getVal04());
        Lists save = listRepository.save(lists1);
        return new ApiResponse(true, "muvaffaqiyatli saqlandi",save);
    }
}
