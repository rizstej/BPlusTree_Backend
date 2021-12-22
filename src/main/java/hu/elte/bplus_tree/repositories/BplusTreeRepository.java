/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hu.elte.bplus_tree.repositories;

import hu.elte.bplus_tree.entities.BplussTree;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/**
 *
 * @author Lilla
 */
@Repository
public interface BplusTreeRepository extends CrudRepository<BplussTree, Integer>{
}
