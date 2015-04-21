SELECT `economy`.`year`,
    `economy`.`city_gdp`,
    `economy`.`energy_consume_per_gdp`,
    `economy`.`population`,
    `economy`.`foreign_investment`,
    `economy`.`export_trade`,
    `economy`.`retail_sale`,
    `economy`.`import_export_trade`,
    `economy`.`industry_increment`,
    `economy`.`month`,
    `economy`.`id`,
	`economy`.`tax`
FROM `forecasting`.`economy`;
