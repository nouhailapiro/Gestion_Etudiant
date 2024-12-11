import { useState } from 'react';
import axios from 'axios';
import { useNavigate, useParams } from 'react-router-dom';

const AjouterNote = () => {
  const { id } = useParams();
  const [nomCours, setNomCours] = useState('');
  const [noteCours, setNoteCours] = useState('');
  const navigate = useNavigate();

  const handleSubmit = (e) => {
    e.preventDefault();

    const noteData = {
      nomCours,
      noteCours: parseFloat(noteCours),
      idEtudiant: parseInt(id),
    };

    axios.post('http://localhost:8080/api/notes', noteData)
      .then(() => {
        alert('Note ajoutée avec succès');
        navigate(`/etudiants/${id}`); // Retour à la page des notes
      })
      .catch((error) => {
        console.error('Erreur lors de l’ajout de la note :', error);
      });
  };

  return (
    <div>
      <h1>Ajouter une note</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Nom du cours :</label>
          <input
            type="text"
            value={nomCours}
            onChange={(e) => setNomCours(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Valeur de la note :</label>
          <input
            type="number"
            step="0.1"
            value={noteCours}
            onChange={(e) => setNoteCours(e.target.value)}
            required
          />
        </div>
        <button type="submit">Ajouter</button>
      </form>
    </div>
  );
};

export default AjouterNote;
