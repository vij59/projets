
CREATE TABLE public.personne (
                id_personne VARCHAR NOT NULL,
                nom VARCHAR NOT NULL,
                prenom VARCHAR NOT NULL,
                mail VARCHAR NOT NULL,
                CONSTRAINT personne_pk PRIMARY KEY (id_personne)
);


CREATE TABLE public.utilisateur (
                id_utilisateur VARCHAR NOT NULL,
                mot_de_passe VARCHAR NOT NULL,
                id_personne VARCHAR NOT NULL,
                CONSTRAINT utilisateur_pk PRIMARY KEY (id_utilisateur)
);


CREATE TABLE public.site (
                id_site VARCHAR NOT NULL,
                nom_site VARCHAR NOT NULL,
                pays VARCHAR NOT NULL,
                ville VARCHAR NOT NULL,
                code_postal INTEGER NOT NULL,
                CONSTRAINT site_pk PRIMARY KEY (id_site)
);


CREATE TABLE public.commentaire (
                id_commentaire BIGINT NOT NULL,
                commentaire TEXT NOT NULL,
                id_site VARCHAR NOT NULL,
                id_utilisateur VARCHAR NOT NULL,
                CONSTRAINT commentaire_pk PRIMARY KEY (id_commentaire)
);


CREATE TABLE public.topo (
                id_topo VARCHAR NOT NULL,
                id_site VARCHAR NOT NULL,
                nom_topo VARCHAR NOT NULL,
                CONSTRAINT topo_pk PRIMARY KEY (id_topo)
);


CREATE TABLE public.emprunt (
                id_emprunt VARCHAR NOT NULL,
                id_utilisateur VARCHAR NOT NULL,
                date_emprunt VARCHAR NOT NULL,
                id_topo VARCHAR NOT NULL,
                CONSTRAINT emprunt_pk PRIMARY KEY (id_emprunt)
);


CREATE TABLE public.secteur (
                id_secteur VARCHAR NOT NULL,
                id_site VARCHAR NOT NULL,
                CONSTRAINT secteur_pk PRIMARY KEY (id_secteur)
);


CREATE TABLE public.voie (
                id_voie VARCHAR NOT NULL,
                id_secteur VARCHAR NOT NULL,
                cotation VARCHAR NOT NULL,
                CONSTRAINT voie_pk PRIMARY KEY (id_voie)
);


CREATE TABLE public.longueur (
                id_longueur VARCHAR NOT NULL,
                id_voie VARCHAR NOT NULL,
                id_relais VARCHAR NOT NULL,
                CONSTRAINT longueur_pk PRIMARY KEY (id_longueur)
);


ALTER TABLE public.utilisateur ADD CONSTRAINT personne_utilisateur_fk
FOREIGN KEY (id_personne)
REFERENCES public.personne (id_personne)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.commentaire ADD CONSTRAINT utilisateur_commentaire_fk
FOREIGN KEY (id_utilisateur)
REFERENCES public.utilisateur (id_utilisateur)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.emprunt ADD CONSTRAINT utilisateur_emprunt_fk
FOREIGN KEY (id_utilisateur)
REFERENCES public.utilisateur (id_utilisateur)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.secteur ADD CONSTRAINT site_secteur_fk
FOREIGN KEY (id_site)
REFERENCES public.site (id_site)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.topo ADD CONSTRAINT site_topo_fk
FOREIGN KEY (id_site)
REFERENCES public.site (id_site)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.commentaire ADD CONSTRAINT site_commentaire_fk
FOREIGN KEY (id_site)
REFERENCES public.site (id_site)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.emprunt ADD CONSTRAINT topo_emprunt_fk
FOREIGN KEY (id_topo)
REFERENCES public.topo (id_topo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.voie ADD CONSTRAINT secteur_voie_fk
FOREIGN KEY (id_secteur)
REFERENCES public.secteur (id_secteur)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.longueur ADD CONSTRAINT voie_longueur_fk
FOREIGN KEY (id_voie)
REFERENCES public.voie (id_voie)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
