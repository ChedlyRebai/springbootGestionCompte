package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entities.Retrait;
import com.example.demo.entities.Versement;

public interface VersementRepository extends JpaRepository<Versement,Long>{

}

