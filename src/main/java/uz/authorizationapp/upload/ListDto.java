package uz.authorizationapp.upload;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListDto {

    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long type_id;

    private String name_uz;

    private String name_ru;

    private String name_la;

    private String name_en;

    private int int01;

    private int int02;

    private int int03;

    private int int04;

    private String val01;

    private String val02;

    private String val03;

    private String val04;
}
