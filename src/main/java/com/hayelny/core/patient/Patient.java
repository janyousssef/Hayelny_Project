package com.hayelny.core.patient;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Patient {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDateTime dob;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime modifiedAt;
    @Embedded
    @Enumerated(EnumType.STRING)
    private Gender gender;


}
