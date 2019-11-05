create table country(
    id serial,
    name text,
    top_level_domain text[],
    population int8,
    lat_ing int4[],
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

alter table currency
    add constraint currency_country_fk
        foreign key (country_id) references country;

alter table language
    add constraint language_country_fk
        foreign key (country_id) references country;

alter table regional_blocs
    add constraint regional_blocs_country_fk
        foreign key (country_id) references country;

alter table translations
    add constraint translations_country_fk
        foreign key (country_id) references country;

