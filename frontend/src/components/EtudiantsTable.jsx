import React, {useEffect, useState} from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const EtudiantTable = () => {
  const [etudiants, setEtudiants] = useState([]);
  const [newNom, setNewNom] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    axios.get('http://localhost:8080/api/etudiants')
      .then((response) => {
        setEtudiants(response.data);
      })
      .catch((error) => {
        console.error("Erreur lors de la récupération des étudiants:", error)
      });
  }, []);

  const addEtudiant = (event) => {
    event.preventDefault();
    if (newNom.trim() === "") {
      alert("Le nom de l'étudiant ne peut pas être vide !");
      return;
    }

    axios.post('http://localhost:8080/api/etudiants', { nom: newNom })
      .then((response) => {
        setEtudiants([...etudiants, response.data]); // Ajouter le nouvel étudiant à la liste
        setNewNom(""); // Réinitialiser le champ de saisie
      })
      .catch((error) => {
        console.error("Erreur lors de l'ajout de l'étudiant :", error);
      });
  };

  const handleEtudiantClick = (id) => {
    navigate(`/etudiants/${id}`);
  };

  return(
    <div>
      <h1>Liste des Étudiants</h1>

      <form onSubmit={addEtudiant}>
        <input
          type="text"
          placeholder="Nom de l'étudiant"
          value={newNom}
          onChange={(e) => setNewNom(e.target.value)}
        />
        <button type="submit">Ajouter Étudiant</button>
      </form>

      <table border="1">
        <thead>
          <tr>
            <th>Nom</th>
            <th>Date de Création</th>
          </tr>
        </thead>
        <tbody>
          {etudiants.map((etudiant) => (
            <tr key={etudiant.id} onClick={() => handleEtudiantClick(etudiant.id)}>
              <td>{etudiant.nom}</td>
              <td>{etudiant.dateCreation}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default EtudiantTable;