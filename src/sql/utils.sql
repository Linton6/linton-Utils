-- �����޸ı��ǰ׺
SELECT
    CONCAT(
        'ALTER TABLE ',
        table_name,
        ' RENAME TO s_',
        substring(table_name, 4),
        ';'
    )
FROM
    information_schema.TABLES
WHERE
    table_name LIKE 'Sp_%';