
    select
        ton_akkord.akkord_id,
        ton_akkord.ton_id,
        ton_akkord.position,
        ton.`tonnameVollstaendig`
    from
        `java`.`ton_akkord` ton_akkord
        , `java`.`ton` ton
    where
      ton_akkord.ton_id = ton.id
      -- and ton_akkord.akkord_id > 1112
    order by
      ton_akkord.akkord_id, position 