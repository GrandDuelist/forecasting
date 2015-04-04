
-- INSERT INTO `forecasting`.`economy`
-- (`year`,
-- `cityGDP`,
-- `单位GDP能耗`,
-- ` 人口数`,
-- `实际利用外资`,
-- `货物一般贸易出口总额`,
-- `社会消费品零售总额`,
-- `进出口贸易总额`,
-- `工业增加值`)
-- VALUES
-- (1980,
-- 311.89,
-- 6.34,
-- 1162.8,
-- 0.6307,
-- 3.65,
-- 80.43,
-- 45.06,
-- 230.87);
SELECT `economy`.`year`,
    `economy`.`cityGDP`,
    `economy`.`单位GDP能耗`,
    `economy`.` 人口数`,
    `economy`.`实际利用外资`,
    `economy`.`货物一般贸易出口总额`,
    `economy`.`社会消费品零售总额`,
    `economy`.`进出口贸易总额`,
    `economy`.`工业增加值`
FROM `forecasting`.`economy`;

