package ru.zagrebin.laba6.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {
    private int auditId;
    private String tableName;
    private String operationType;
    private String recordId;
    private String oldData;
    private String newData;
    private java.sql.Timestamp changedAt;
    private String changedBy;
}
