import React, {useState, useEffect} from 'react';
import {useParams} from 'react-router-dom';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import '../index.css'


const Notes = () => {
  const {id} = useParams();
  const [notes, setNotes] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    axios.get(`http://api.exam-kubernetes.com/api/notes/etudiant/${id}`)
      .then((response) => {
        setNotes(response.data)
      })
      .catch((error) => console.error('Erreur lors de la récupération des notes :', error));
  }, [id]);

  return (
    <div>
      <h1>Notes de l'Étudiant</h1>
      { notes.length != 0 ? <table border="1">
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
              <td className={note.valeurNote >= 10 ? "green" : "red"}>{note.valeurNote}</td>
            </tr>
          ))}
        </tbody>
      </table> : <p>Cet étudiant n'a pas encore de notes !</p>}
      
      <button onClick={() => navigate(`/etudiant/${id}/ajouterNote`)}>Ajouter une note</button>
    </div>
  );
};

export default Notes;