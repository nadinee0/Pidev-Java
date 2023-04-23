/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev.techstorm.entities;

/**
 *
 * @author USER
 */
public class Chatbot {
    public String processInput(String input) {
       if(null == input)return "Je suis désolé, mais votre demande n'est pas claire pour moi. \n Pourriez-vous donner plus de détails sur ce que vous \n cherchez à faire ?";
       else switch (input) {
            case "bonjour":
                return "Bonjour, comment puis-je vous aider ?";
            case "Pouvez vous m'expliquer le concept de cette plateforme":
                return "TECHSTORM est une plateforme de e-sports qui \n offre une expérience unique de streaming, de \n réservation d'événements et d'achat de produits etc... " ;
            case "Comment je peux participer a un événement?":
                return "Notre plateforme vous permet d'accéder à une  \n grande variété d'événements et de réserver \n des billets en quelques clics. Inscrivez-vous, \n choisissez l'événement qui vous intéresse\n et achetez un billet. " ;
           
            case "Merci":
                return "A tout moment , je suis là pour vous aidez !";
              
            default:
                return "Je suis désolé, mais votre demande n'est pas claire pour moi. Pourriez-vous donner plus de détails sur ce que vous cherchez à faire ?";
        }
    }
    
}
