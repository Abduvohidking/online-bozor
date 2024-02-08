package uz.authorizationapp.upload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    boolean success;
    String message;
    Object data;

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
