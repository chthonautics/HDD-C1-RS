package cl.usm.hddc1rs.entities;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class Book {
    @Id
    @NotBlank(message = "El ISBN es obligatorio")
    @Size(min = 13, max = 13, message = "El ISBN debe tener 13 caracteres")
    private String isbn;

    @NotBlank(message = "El titulo es obligatorio")
    private String titulo;

    @NotBlank(message = "Se debe incluir un autor")
    private String autor;

    @NotNull(message = "Se debe incluir el numero de paginas")
    @Min(value = 10, message = "El numero de paginas debe ser mayor a 10")
    private Integer paginas; // at least 10

    @NotBlank(message = "Se requiere una categoria")
    private String categoria;
}
