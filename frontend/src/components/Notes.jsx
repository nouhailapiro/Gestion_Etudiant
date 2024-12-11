import React, {useState, useEffect} from 'react';
import {useParams} from 'react-router-dom';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';


const Notes = () => {
  const {id} = useParams();
  const [notes, setNotes] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    axios.get(`http://localhost:8080/api/notes/etudiant/${id}`)
      .then((response) => {
        setNotes(response.data)
      })
      .catch((error) => console.error('Erreur lors de la récupération des notes :', error));
  }, [id]);

  return (
    <div>
      <h1>Notes de l'Étudiant</h1>
      <table border="1">
        <thead>
          <tr>
            <th>Nom du Cours</th>
            <th>Note</th>
          </tr>
        </thead>
        <tbody>
          {notes.map((note) => (
            <tr key={note.id}>
              <td>{note.nomCours}</td>
              <td>{note.valeurNote}</td>
            </tr>
          ))}
        </tbody>
      </table>
      <button onClick={() => navigate(`/etudiant/${id}/ajouterNote`)}>Ajouter une note</button>
    </div>
  );
};

export default Notes;