<?php

namespace ProductBundle\Controller;
use MyBundle\Entity\Category;
use MyBundle\Form\ProductType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use MyBundle\Entity\Product;
use Symfony\Component\HttpFoundation\Response;
use MyBundle\Repository\ProductRepository;
use Symfony\Component\Form\Form;


use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;


class ProductController extends Controller
{
    public function addproductAction(Request $request)
    {
        $category = new Product();

        $form = $this->createForm(ProductType::class,$category);

        $form->handleRequest($request);

        if($form->isSubmitted()&& $form->isValid())
        {
            $entityManager = $this->getDoctrine()->getManager();

            $entityManager->persist($category);

            $entityManager->flush();



        }

        return $this->render('@Product/Product/addproduct.html.twig', array('form'=> $form->createView()));
    }

    public function showproductAction(Request $request)
    {
        $em = $this->getDoctrine();
        $product=$em->getRepository(Product::class)->findAll();

        return $this->render('@Product/Product/showproduct.html.twig', array('product'=>$product));
    }
    public function detailAction($idproduct)
    {
        $product=$this->getDoctrine()->getRepository(Product::class)->find($idproduct);

        return $this->render('@Product/Product/detailproduct.html.twig', array('product'=>$product));

    }
    public function deleteproductAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $v=$em->getRepository(Product::class)->find($request->get('idproduct'));
        $em->remove($v);
        $em->flush();
        return $this->redirectToRoute('showproduct');
    }


    public function sortAction($sort)
    {

        $entityManager = $this->getDoctrine()->getManager();

        if ($sort=='ASC'){
            $query = $entityManager->createQuery(
                'SELECT p
    FROM MyBundle:product p
    ORDER BY p.productprice ASC'
            );
        }else {
            $query = $entityManager->createQuery(
                'SELECT p
    FROM MyBundle:product p
    ORDER BY p.productprice  DESC'
            );
        }



        $product = $query->getResult();

        return $this->render('@Product/Product/sort.html.twig', array('product'=>$product));
    }
    public function searchAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $requestString = $request->get('q');
        $produit=$em->getRepository(Product::class)->find('productName');
        if(!$produit)
        {
            $result['Produit']['error']="Produit introuvable :( ";

        }else{
            $result['Produit']=$this->getRealEntities($produit);
        }
        return new Response(json_encode($result));

    }
    public function getRealEntities($produit){
        foreach ($produit as $produit){
            $realEntities[$produit->getIdproduct()] = [$produit->getProductname(), $produit->getProductprice(),$produit->getCategoryid(),$produit->getProductpic()];
        }
        return $realEntities;
    }
    public function editprodAction(Request $request,$idproduct)
    {

        $formation=$this->getDoctrine()->getRepository(Product::class)->find($idproduct);

        $form = $this->createForm(ProductType::class,$formation);

        $form->handleRequest($request);
        if($form->isSubmitted()&& $form->isValid())
        {
            $entityManager = $this->getDoctrine()->getManager();




            $entityManager->flush();

            return $this->redirectToRoute('showproduct');

        }

        return $this->render('@Product/Product/editprod.html.twig', array('form'=> $form->createView()));

    }




}
