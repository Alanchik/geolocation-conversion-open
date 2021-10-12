create table geolocations (
    id serial primary key not null,
    search_hash varchar(255) unique,
    address varchar(255) not null,
    lon varchar(255) not null,
    lat varchar(255) not null
);

create index idx_search_hash on geolocations(search_hash);

create index idx_lon_lat on geolocations(lon, lat);

