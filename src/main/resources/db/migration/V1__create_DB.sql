create table country(
    id serial,
    name text,
    top_level_domain text[],
    population int8,
    latlng text[],
    flag text,
    primary key (id)
);

create table currency(
    id serial,
    code text,
    name text,
    symbol text,
    country_id int4,
    primary key (id)
);

create table language(
    id serial,
    iso639_1 text,
    iso639_2 text,
    name text,
    native_name text,
    country_id int4,
    primary key (id)
);

create table regional_blocs(
    id serial,
    acronym text,
    name text,
    other_acronyms text[],
    other_names text[],
    country_id int4,
    primary key (id)
);

create table translations(
    id serial,
    de text,
    es text,
    fr text,
    ja text,
    it text,
    br text,
    pt text,
    nl text,
    hr text,
    fa text,
    country_id int4,
    primary key (id)
);

